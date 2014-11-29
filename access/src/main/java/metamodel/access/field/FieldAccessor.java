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
package metamodel.access.field;

import metamodel.field.AbstractField;

/**
 * @author Michael Kroll
 */
public class FieldAccessor<BASE, TYPE> {
	private final BASE object;
	private final AbstractField<? super BASE, TYPE> fieldDefinition;

	public FieldAccessor(final BASE object, final AbstractField<? super BASE, TYPE> fieldDefinition) {
		this.object = object;
		this.fieldDefinition = fieldDefinition;
	}

	public void set(final TYPE value) throws NoSuchFieldException, SecurityException, IllegalArgumentException,
	        IllegalAccessException {
		FieldHelper.set(object, fieldDefinition, value);
	}

	public TYPE get() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		return FieldHelper.get(object, fieldDefinition);
	}
}
