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
package metamodel.core;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import metamodel.field.ArrayField;
import metamodel.field.PluralField;
import metamodel.field.SingularField;

import com.sun.codemodel.ClassType;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JDocComment;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;

/**
 *
 * @author MiKro
 *
 */
public class ModelBuilder {

	public JCodeModel buildCodeModel(final Set<Class<?>> classes) {
		final Map<Class<?>, JDefinedClass> definedClasses = new HashMap<>();
		final JCodeModel codeModel = new JCodeModel();
		for (final Class<?> clazz : classes) {
			try {
				if (clazz.isMemberClass()) {
					throw new IllegalArgumentException(
					        "inner classes are not supported for explicit metamodel generation. They are generated along with their parent classes. class was "
					                + clazz.getName());
				}
				final JDefinedClass classCodeModel = codeModel._class(JMod.PUBLIC | JMod.ABSTRACT,
				        getCorrespondingMetamodelClassName(clazz),
				        ClassType.CLASS);
				defineClass(codeModel, classCodeModel, clazz, definedClasses);
			} catch (final JClassAlreadyExistsException e) {
				throw new RuntimeException(e);
			}
		}

		// 2. run through all classes that were defined and add "extends" where possible/needed
		for (final Entry<Class<?>, JDefinedClass> entry : definedClasses.entrySet()) {
			final Class<?> clazz = entry.getKey();
			final JDefinedClass classCodeModel = entry.getValue();
			if (!Object.class.equals(clazz.getSuperclass())) {
				final JDefinedClass superClassDefinition = definedClasses.get(clazz.getSuperclass());
				if (superClassDefinition != null) {
					classCodeModel._extends(superClassDefinition);
				} else {
					System.out.println("Class " + clazz.getName() + " has superclass "
					        + clazz.getSuperclass().getName() + " which is not included in generation context.");
					final JDocComment javadoc = classCodeModel.javadoc();
					javadoc.add("Class " + clazz.getName() + " has superclass " + clazz.getSuperclass().getName()
					        + " which is not included in generation context.");
				}
			}
		}

		return codeModel;
	}

	private String getCorrespondingMetamodelClassName(final Class<?> clazz) {
		if (clazz.isMemberClass()) {
			return clazz.getSimpleName() + "_";
		} else {
			return clazz.getName() + "_";
		}
	}

	private void defineClass(final JCodeModel codeModel, final JDefinedClass classCodeModel, final Class<?> clazz,
	        final Map<Class<?>, JDefinedClass> definedClasses) {

		for (final Field field : clazz.getDeclaredFields()) {
			if (Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			addField(codeModel, classCodeModel, field);
		}

		for (final Class<?> innerClass : clazz.getDeclaredClasses()) {
			try {
				// inner classes need to be static for inheritance to work without defining constructors
				final JDefinedClass innerClassModel =
				        classCodeModel._class(JMod.PUBLIC | JMod.STATIC | JMod.ABSTRACT,
				                getCorrespondingMetamodelClassName(innerClass), ClassType.CLASS);
				defineClass(codeModel, innerClassModel, innerClass, definedClasses);
			} catch (final JClassAlreadyExistsException e) {
				throw new RuntimeException(e);
			}
		}

		definedClasses.put(clazz, classCodeModel);
	}

	private void addField(final JCodeModel codeModel, final JDefinedClass classCodeModel, final Field field) {
		final JClass baseType = codeModel.ref(field.getDeclaringClass());
		final Class<?> fieldType = field.getType();
		if (fieldType.isPrimitive()) {
			final JClass rawLLclazz = codeModel.ref(SingularField.class);
			final JClass fieldClazz = rawLLclazz.narrow(baseType, convertType(codeModel, fieldType));
			classCodeModel.field(JMod.PUBLIC | JMod.STATIC | JMod.VOLATILE, fieldClazz, field.getName());
		} else if (fieldType.isArray()) {
			if (field.getGenericType() instanceof GenericArrayType) {
				final GenericArrayType genericArrayType = (GenericArrayType) field.getGenericType();
				final JClass rawLLclazz = codeModel.ref(ArrayField.class);
				final JClass fieldClazz = rawLLclazz.narrow(baseType,
				        convertType(codeModel, genericArrayType.getGenericComponentType()));
				classCodeModel.field(JMod.PUBLIC | JMod.STATIC | JMod.VOLATILE, fieldClazz, field.getName());
			} else {
				final JClass rawLLclazz = codeModel.ref(ArrayField.class);
				final JClass fieldClazz = rawLLclazz.narrow(baseType,
				        convertType(codeModel, fieldType.getComponentType()));
				classCodeModel.field(JMod.PUBLIC | JMod.STATIC | JMod.VOLATILE, fieldClazz, field.getName());
			}
		} else {
			if (Collection.class.isAssignableFrom(fieldType)) {
				JClass collectionElementType;
				final JClass collectionType = convertType(codeModel, field.getGenericType());
				if (field.getGenericType() instanceof ParameterizedType) {
					final ParameterizedType genericType = (ParameterizedType) field.getGenericType();
					collectionElementType = convertType(codeModel, genericType.getActualTypeArguments()[0]);
				} else {
					collectionElementType = codeModel.ref(Object.class);
				}
				final JClass rawLLclazz = codeModel.ref(PluralField.class);
				final JClass fieldClazz = rawLLclazz.narrow(baseType, collectionType, collectionElementType);
				classCodeModel.field(JMod.PUBLIC | JMod.STATIC | JMod.VOLATILE, fieldClazz, field.getName());
			} else if (Map.class.isAssignableFrom(fieldType)) {
				JClass collectionElementType;
				final JClass collectionType = convertType(codeModel, field.getGenericType());
				if (field.getGenericType() instanceof ParameterizedType) {
					final ParameterizedType genericType = (ParameterizedType) field.getGenericType();
					collectionElementType = convertType(codeModel, genericType.getActualTypeArguments()[1]);
				} else {
					collectionElementType = codeModel.ref(Object.class);
				}

				final JClass rawLLclazz = codeModel.ref(PluralField.class);
				final JClass fieldClazz = rawLLclazz.narrow(baseType, collectionType, collectionElementType);
				classCodeModel.field(JMod.PUBLIC | JMod.STATIC | JMod.VOLATILE, fieldClazz, field.getName());
			} else {
				final JClass rawLLclazz = codeModel.ref(SingularField.class);
				final JClass fieldClazz = rawLLclazz.narrow(baseType, convertType(codeModel, field.getGenericType()));

				classCodeModel.field(JMod.PUBLIC | JMod.STATIC | JMod.VOLATILE, fieldClazz, field.getName());
			}
		}
	}

	private JClass convertType(final JCodeModel codeModel, final Type possibleGenericType) {
		if (possibleGenericType instanceof WildcardType) {
			final WildcardType wildcardType = (WildcardType) possibleGenericType;
			if (wildcardType.getUpperBounds().length > 0) {
				// ? extends XYZ
				final JClass upperBound = convertType(codeModel, wildcardType.getUpperBounds()[0]);
				return upperBound.wildcard();
			}
			return codeModel.ref(Object.class);
		}
		if (possibleGenericType instanceof ParameterizedType) {
			final ParameterizedType genericType = (ParameterizedType) possibleGenericType;
			final JClass[] convertedTypeArguments = new JClass[genericType.getActualTypeArguments().length];
			for (int i = 0; i < genericType.getActualTypeArguments().length; i++) {
				convertedTypeArguments[i] = convertType(codeModel, genericType.getActualTypeArguments()[i]);
			}
			final JClass rawLLclazz = codeModel.ref((Class<?>) genericType.getRawType());
			return rawLLclazz.narrow(convertedTypeArguments);
		}
		if (possibleGenericType instanceof GenericArrayType) {
			final GenericArrayType genericArrayType = (GenericArrayType) possibleGenericType;
			return convertType(codeModel, genericArrayType.getGenericComponentType()).array();
		}

		final JType type = codeModel._ref((Class<?>) possibleGenericType);
		return type.boxify();
	}
}
