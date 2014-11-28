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
package metamodel.access.method;

import java.lang.reflect.InvocationTargetException;

import metamodel.method.Method2;

/**
 * @author Michael Kroll
 *
 */
public class Callable2<BASE, RT, P1, P2> {

	private final BASE object;
	private final Method2<BASE, RT, P1, P2> methodDefinition;

	public Callable2(final BASE object, final Method2<BASE, RT, P1, P2> methodDefinition) {
		this.object = object;
		this.methodDefinition = methodDefinition;
	}

	public RT invoke(final P1 param1, final P2 param2) throws NoSuchMethodException, SecurityException,
	        IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return InvocationHelper.invoke(object, methodDefinition, param1, param2);
	}
}
