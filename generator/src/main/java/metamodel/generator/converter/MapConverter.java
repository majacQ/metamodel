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

import java.util.List;
import java.util.Map;

import metamodel.field.MapField;
import metamodel.field.impl.MapFieldImpl;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JInvocation;

/**
 * FieldCoverter for Maps.
 *
 * @author Michael Kroll
 */
public class MapConverter implements FieldConverter {

	@Override
	public Class<?> getTargetClass() {
		return Map.class;
	}

	@Override
	public FieldDefinition convert(final JCodeModel codeModel, final JClass baseType, final JClass convertedType,
	        final String fieldName) {
		final List<JClass> typeParams = convertedType.getTypeParameters();
		JClass mapKeyType;
		JClass mapValueType;
		if (typeParams.size() == 2) {
			// eg. Map<String, Boolean> --> String, Boolean
			mapKeyType = typeParams.get(0);
			mapValueType = typeParams.get(1);
		} else {
			// eg. Map --> Object, Object
			mapKeyType = codeModel.ref(Object.class);
			mapValueType = codeModel.ref(Object.class);
		}
		final JClass rawLLclazz = codeModel.ref(MapField.class);
		final JClass fieldClass = rawLLclazz.narrow(baseType, convertedType, mapKeyType, mapValueType);
		final JInvocation fieldInit = JExpr._new(codeModel.ref(MapFieldImpl.class).narrow(DIAMOND))
		        .arg(fieldName).arg(baseType.dotclass());

		return new FieldDefinition(fieldClass, fieldInit);
	}
}
