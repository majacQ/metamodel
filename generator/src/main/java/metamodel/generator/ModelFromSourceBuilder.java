/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 madprogger
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package metamodel.generator;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.EnumDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.body.VariableDeclarator;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.PrimitiveType;
import japa.parser.ast.type.ReferenceType;
import japa.parser.ast.type.Type;
import japa.parser.ast.type.WildcardType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import metamodel.field.ArrayField;
import metamodel.field.PluralField;
import metamodel.field.SingularField;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;

/**
 * Builds meta model from source files without compilation.
 *
 * @author madprogger
 */
public class ModelFromSourceBuilder {

	/**
	 * Build metamodel for classes in source files.
	 *
	 * @param sourceFiles source files whose metamodel should be built.
	 * @return metamodel
	 */
	public JCodeModel buildCodeModel(final Set<File> sourceFiles) {
		final Map<TypeDeclaration, JDefinedClass> definedClasses = new HashMap<>();
		final JCodeModel codeModel = new JCodeModel();
		for (final File sourceFile : sourceFiles) {
			try (FileInputStream in = new FileInputStream(sourceFile)) {

				// parse the file
				final CompilationUnit cu = JavaParser.parse(in);
				for (final TypeDeclaration type : cu.getTypes()) {
					if (type instanceof ClassOrInterfaceDeclaration
					        || type instanceof EnumDeclaration) {
						final JDefinedClass classCodeModel = codeModel._class(getFullClassName(cu, type));
						defineClass(codeModel, classCodeModel, cu, type, definedClasses);
					}
				}

			} catch (final IOException | ParseException | JClassAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return codeModel;
	}

	private String getFullClassName(final CompilationUnit cu, final TypeDeclaration type) {
		if (cu.getPackage() != null) {
			final String packageName = cu.getPackage().getName().toString();
			return packageName + "." + type.getName() + "_";
		} else {
			return type.getName() + "_";
		}
	}

	/**
	 * @param codeModel
	 * @param classCodeModel
	 * @param cu
	 * @param type
	 * @param definedClasses
	 */
	private void defineClass(final JCodeModel codeModel, final JDefinedClass classCodeModel,
	        final CompilationUnit cu, final TypeDeclaration classType,
	        final Map<TypeDeclaration, JDefinedClass> definedClasses) {

		final JClass baseType = findType(codeModel, cu, classType.getName());
		for (final BodyDeclaration member : classType.getMembers()) {
			if (member instanceof FieldDeclaration) {
				final FieldDeclaration field = (FieldDeclaration) member;

				if (Modifier.isStatic(field.getModifiers())) {
					continue;
				}
				addField(codeModel, classCodeModel, baseType, cu, classType, field);
			}
		}

		// for (final BodyDeclaration member : type.getMembers()) {
		// if (member instanceof TypeDeclaration) {
		// try {
		// final TypeDeclaration innerType = (TypeDeclaration) member;
		// // inner classes need to be static for inheritance to work without defining constructors
		// final JDefinedClass innerClassModel =
		// classCodeModel._class(JMod.PUBLIC | JMod.STATIC | JMod.ABSTRACT,
		// getCorrespondingMetamodelClassName(innerType), ClassType.CLASS);
		// defineClass(codeModel, innerClassModel, innerType, definedClasses);
		// } catch (final JClassAlreadyExistsException e) {
		// throw new RuntimeException(e);
		// }
		// }
		// }

		definedClasses.put(classType, classCodeModel);
	}

	/**
	 * Add field-definition to metamodel.
	 *
	 * @param codeModel JCodeModel
	 * @param classCodeModel class-definition to fill
	 * @param cu
	 * @param classType
	 * @param field real-world-field
	 */
	private void addField(final JCodeModel codeModel, final JDefinedClass classCodeModel, final JClass baseType,
	        final CompilationUnit cu, final TypeDeclaration classType, final FieldDeclaration field) {
		final Type fieldType = field.getType();
		final JClass convertedType = convertType(codeModel, cu, fieldType);
		final JClass fieldClazz;

		if (convertedType.isPrimitive()) {
			final JClass rawLLclazz = codeModel.ref(SingularField.class);
			fieldClazz = rawLLclazz.narrow(baseType, convertedType);
		} else if (convertedType.isArray()) {
			final JClass rawLLclazz = codeModel.ref(ArrayField.class);
			JClass collectionElementType = convertedType.elementType().boxify();
			while (collectionElementType.isArray()) {
				// find base type. eg. Boolean[][][][] --> Boolean
				collectionElementType = collectionElementType.elementType().boxify();
			}
			fieldClazz = rawLLclazz.narrow(baseType, convertedType, collectionElementType);
		} else if (codeModel.ref(Collection.class).isAssignableFrom(convertedType.erasure())) {
			final List<JClass> typeParams = convertedType.getTypeParameters();
			JClass collectionElementType;
			if (typeParams.size() == 1) {
				// eg. Collection<String> --> String
				collectionElementType = typeParams.get(0);
			} else {
				// eg. Collection --> Object
				collectionElementType = codeModel.ref(Object.class);
			}
			final JClass rawLLclazz = codeModel.ref(PluralField.class);
			fieldClazz = rawLLclazz.narrow(classCodeModel, convertedType, collectionElementType);
		} else if (codeModel.ref(Map.class).isAssignableFrom(convertedType.erasure())) {
			final List<JClass> typeParams = convertedType.getTypeParameters();
			JClass collectionElementType;
			if (typeParams.size() == 2) {
				// eg. Map<String, Boolean> --> Boolean
				collectionElementType = typeParams.get(1);
			} else {
				// eg. Map --> Object
				collectionElementType = codeModel.ref(Object.class);
			}
			final JClass rawLLclazz = codeModel.ref(PluralField.class);
			fieldClazz = rawLLclazz.narrow(classCodeModel, convertedType, collectionElementType);
		} else {
			final JClass rawLLclazz = codeModel.ref(SingularField.class);
			fieldClazz = rawLLclazz.narrow(baseType, convertedType);
		}

		for (final VariableDeclarator variable : field.getVariables()) {
			final JFieldVar f = classCodeModel.field(JMod.PUBLIC | JMod.STATIC | JMod.VOLATILE, fieldClazz, variable
			        .getId()
			        .getName());
			f.javadoc().add(fieldType.toString());
		}
	}

	/**
	 * @param codeModel
	 * @param classCodeModel
	 * @param cu
	 * @param classType
	 * @return
	 */
	private JClass findType(final JCodeModel codeModel,
	        final CompilationUnit cu, final String typeName) {
		if (cu.getImports() != null) {
			for (final ImportDeclaration imp : cu.getImports()) {
				if (imp.getName().toString().endsWith("." + typeName)) {
					return codeModel.ref(imp.getName().toString());
				}
			}
		}
		return codeModel.ref(typeName);
	}

	/**
	 * Convert a {@link Type} to JClass. This includes generic type information.
	 *
	 * @param codeModel JCodeModel
	 * @param possibleGenericType type to convert
	 * @return converted type
	 */
	private JClass convertType(final JCodeModel codeModel, final CompilationUnit cu, final Type possibleGenericType) {
		if (possibleGenericType instanceof PrimitiveType) {
			// int, boolean, short, ...
			final PrimitiveType type = (PrimitiveType) possibleGenericType;
			return JType.parse(codeModel, type.getType().toString().toLowerCase()).boxify();
		} else if (possibleGenericType instanceof ReferenceType) {
			final ReferenceType type = (ReferenceType) possibleGenericType;
			if (type.getArrayCount() == 0) {
				// String, Boolean, Collection<String>, ...
				return convertType(codeModel, cu, type.getType());
			} else {
				// String[], Boolean[][][], Collection<String>[], ...
				JClass elementType = convertType(codeModel, cu, type.getType());
				for (int i = 0; i < type.getArrayCount(); i++) {
					// add [] as much as needed
					elementType = elementType.array();
				}
				return elementType;
			}
		} else if (possibleGenericType instanceof WildcardType) {
			final WildcardType wildcardType = (WildcardType) possibleGenericType;
			final ReferenceType extend = wildcardType.getExtends();
			if (extend != null) {
				// List<? extends Something>, ...
				final JClass upperBound = convertType(codeModel, cu, extend);
				return upperBound.wildcard();
			}
			// List<?>, MyClass<?>, ...
			return codeModel.wildcard();
		} else if (possibleGenericType instanceof ClassOrInterfaceType) {
			final ClassOrInterfaceType type = (ClassOrInterfaceType) possibleGenericType;
			final JClass baseType = findType(codeModel, cu, type.getName());
			if (type.getTypeArgs() == null) {
				// String
				return baseType;
			} else {
				// List<String>, List<Map<?, ?>>, ...
				final JClass[] convertedTypeArgs = new JClass[type.getTypeArgs().size()];
				for (int i = 0; i < type.getTypeArgs().size(); i++) {
					final Type typeArg = type.getTypeArgs().get(i);
					convertedTypeArgs[i] = convertType(codeModel, cu, typeArg);
				}
				return baseType.narrow(convertedTypeArgs);
			}
		}
		// should not get here
		// return codeModel.ref(possibleGenericType.toString());
		throw new IllegalArgumentException("cannot convert " + possibleGenericType.toString());
	}
}
