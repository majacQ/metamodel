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
package metamodel.access;

import java.lang.reflect.Field;

import metamodel.field.AbstractField;

/**
 * Provides access to fields-values of objects using reflection and metamodel-description of the class.
 *
 * @author Michael Kroll
 */
public class AccessorUtil {

	public static <BASE, TYPE> void set(final BASE object,
	        final AbstractField<BASE, TYPE> fieldDefinition,
	        final TYPE value) throws NoSuchFieldException, SecurityException, IllegalArgumentException,
	        IllegalAccessException {
		final Field field = getAccessibleField(fieldDefinition);
		field.set(object, value);
	}

	@SuppressWarnings("unchecked")
	public static <BASE, TYPE> TYPE get(final BASE object, final AbstractField<BASE, TYPE> fieldDefinition)
	        throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final Field field = getAccessibleField(fieldDefinition);
		return (TYPE) field.get(object);
	}

	/**
	 * Get Field of Class as defined by metamodel field definition.
	 *
	 * @param fieldDefinition metamodel field definition
	 * @return Field
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private static Field getAccessibleField(final AbstractField<?, ?> fieldDefinition)
	        throws NoSuchFieldException, SecurityException {
		final String fieldName = fieldDefinition.getName();
		final Field field = fieldDefinition.getDeclaringClass().getDeclaredField(fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		return field;
	}
}
