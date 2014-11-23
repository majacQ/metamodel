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
package metamodel.field.impl;

import metamodel.field.ArrayField;

/**
 * Implementation for array-field-definition. If a field is defined as {@code X[] field;}, then this kind of
 * field-definition is used.
 *
 * @author Michael Kroll
 * @param <BASE> type of class that declares the field
 * @param <COL> type of aggregating array, eg. Boolean[][][]
 * @param <ELEM> type of associated values
 */
public class ArrayFieldImpl<BASE, COL, ELEM> extends MetaFieldImpl<BASE, COL> implements ArrayField<BASE, COL, ELEM> {

	/**
	 * Constructor.
	 *
	 * @param name of the field
	 * @param declaringClass class that declares the field
	 */
	public ArrayFieldImpl(final String name, final Class<BASE> declaringClass) {
		super(name, declaringClass);
	}
}
