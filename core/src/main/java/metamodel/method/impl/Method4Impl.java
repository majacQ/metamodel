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
package metamodel.method.impl;

import metamodel.method.Method4;

/**
 * Implementation for method-definitions with 4 parameters.
 * <p>
 * For Methods with return type {@code void}, the type parameter RT is {@link Void}.
 *
 * @author Michael Kroll
 * @param <BASE> type of class that declares the method
 * @param <RT> return type
 * @param <P1> type of first parameter
 * @param <P2> type of second parameter
 * @param <P3> type of third parameter
 * @param <P4> type of 4th parameter
 */
public class Method4Impl<BASE, RT, P1, P2, P3, P4> extends AbstractMethodImpl<BASE, RT>
        implements Method4<BASE, RT, P1, P2, P3, P4> {

	public Method4Impl(final String name, final Class<BASE> declaringClass, final Class<?> param1Class,
	        final Class<?> param2Class, final Class<?> param3Class, final Class<?> param4Class) {
		super(name, declaringClass, param1Class, param2Class, param3Class, param4Class);
	}
}
