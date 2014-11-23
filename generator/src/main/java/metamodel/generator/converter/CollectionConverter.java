/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Michael Kroll
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
package metamodel.generator.converter;

import java.util.Collection;
import java.util.List;

import metamodel.field.CollectionField;
import metamodel.field.impl.CollectionFieldImpl;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JInvocation;

/**
 * FieldCoverter for Collections.
 *
 * @author madprogger
 */
public class CollectionConverter implements FieldCoverter {

	@Override
	public Class<?> getTargetClass() {
		return Collection.class;
	}

	@Override
	public FieldDefinition convert(final JCodeModel codeModel, final JClass baseType, final JClass convertedType,
	        final String fieldName) {
		final List<JClass> typeParams = convertedType.getTypeParameters();
		JClass collectionElementType;
		if (typeParams.size() == 1) {
			// eg. Collection<String> --> String
			collectionElementType = typeParams.get(0);
		} else {
			// eg. Collection --> Object
			collectionElementType = codeModel.ref(Object.class);
		}
		final JClass rawLLclazz = codeModel.ref(CollectionField.class);
		final JClass fieldClass = rawLLclazz.narrow(baseType, convertedType, collectionElementType);
		final JInvocation fieldInit = JExpr._new(codeModel.ref(CollectionFieldImpl.class).narrow(DIAMOND))
		        .arg(fieldName).arg(baseType.dotclass());

		return new FieldDefinition(fieldClass, fieldInit);
	}
}
