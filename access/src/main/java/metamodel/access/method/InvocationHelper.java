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
import java.lang.reflect.Method;

import metamodel.method.AbstractMethod;

/**
 * @author Michael Kroll
 *
 */
public class InvocationHelper {

	/**
	 * Invokes a defined method on an onject instance.
	 *
	 * @param object Instance with method to call
	 * @param methodDefinition metamodel method definition
	 * @return return value of method invocation, always {@code null} for void methods
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	protected static <BASE, RT, P1> RT invoke(final BASE object, final AbstractMethod<BASE, RT> methodDefinition,
	        final Object... params) throws NoSuchMethodException, SecurityException, IllegalAccessException,
	        IllegalArgumentException, InvocationTargetException {
		final Method method = getAccessibleMethod(methodDefinition);
		return (RT) method.invoke(object, params);
	}

	/**
	 * Get Method of Class as defined by metamodel method definition.
	 *
	 * @param fieldDefinition metamodel method definition
	 * @return Method
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private static <BASE, RT> Method getAccessibleMethod(final AbstractMethod<BASE, RT> methodDefinition)
	        throws NoSuchMethodException, SecurityException {
		final String methodName = methodDefinition.getName();
		final Method method = methodDefinition.getDeclaringClass().getDeclaredMethod(methodName,
		        methodDefinition.getParameterClasses());
		if (!method.isAccessible()) {
			method.setAccessible(true);
		}
		return method;
	}

}
