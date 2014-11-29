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

import metamodel.method.Method5;

/**
 * Call-wrapper for Method with 5 parameters.
 *
 * @author Michael Kroll
 * @param <BASE> type of class that declares the method
 * @param <RT> return type
 * @param <P1> type of first parameter
 * @param <P2> type of second parameter
 * @param <P3> type of third parameter
 * @param <P4> type of 4th parameter
 * @param <P5> type of 5th parameter
 */
public class Callable5<BASE, RT, P1, P2, P3, P4, P5> {

	private final BASE object;
	private final Method5<? super BASE, RT, P1, P2, P3, P4, P5> methodDefinition;

	public Callable5(
	        final BASE object,
	        final Method5<? super BASE, RT, P1, P2, P3, P4, P5> methodDefinition) {
		this.object = object;
		this.methodDefinition = methodDefinition;
	}

	/**
	 * Invoke Method with given parameters.
	 *
	 * @param param1 first parmeter
	 * @param param2 second parameter
	 * @param param3 third parameter
	 * @param param4 4th parameter
	 * @param param5 5th parameter
	 * @return result of method invocation. void-methods always return null
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public RT invoke(final P1 param1, final P2 param2, final P3 param3, final P4 param4, final P5 param5)
	        throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
	        InvocationTargetException {
		return MethodHelper.invoke(object, methodDefinition, param1, param2, param3, param4, param5);
	}
}
