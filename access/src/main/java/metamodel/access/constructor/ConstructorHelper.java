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
package metamodel.access.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import metamodel.constructor.AbstractConstructor;

/**
 * Invokes defined constructors.
 *
 * @author Michael Kroll
 */
public class ConstructorHelper {

	/** Hide Utility Constructor. */
	private ConstructorHelper() {
	}

	/**
	 * Invokes a defined constructor on a class.
	 *
	 * @param constructorDefinition metamodel constructor definition
	 * @return return value of method invocation, always {@code null} for void methods
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	protected static <BASE> BASE invoke(final AbstractConstructor<BASE> constructorDefinition,
	        final Object... params) throws NoSuchMethodException, SecurityException, InstantiationException,
	        IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final Constructor<BASE> constructor = getAccessibleConstructor(constructorDefinition);
		return constructor.newInstance(params);
	}

	/**
	 * Get constructor of class as defined by metamodel constructor definition.
	 *
	 * @param constructorDefinition metamodel constructor definition
	 * @return Constructor
	 * @throws NoSuchMethodException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private static <BASE> Constructor<BASE> getAccessibleConstructor(
	        final AbstractConstructor<BASE> constructorDefinition) throws NoSuchMethodException, SecurityException {
		final Constructor<BASE> constructor = constructorDefinition.getDeclaringClass().getDeclaredConstructor(
		                constructorDefinition.getParameterClasses());
		if (!constructor.isAccessible()) {
			constructor.setAccessible(true);
		}
		return constructor;
	}

}
