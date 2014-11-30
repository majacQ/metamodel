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

import java.lang.reflect.InvocationTargetException;

import metamodel.constructor.Constructor2;

/**
 * Call-wrapper for Constructor with two parameters.
 *
 * @author Michael Kroll
 * @param <BASE> type of class that declares the constructor
 * @param <P1> type of first parameter
 * @param <P2> type of second parameter
 */
public class Constructable2<BASE, P1, P2> {

	private final Constructor2<BASE, P1, P2> constructorDefinition;

	public Constructable2(final Constructor2<BASE, P1, P2> constructorDefinition) {
		this.constructorDefinition = constructorDefinition;
	}

	public BASE invoke(final P1 param1, final P2 param2) throws NoSuchMethodException, SecurityException,
	        InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return ConstructorHelper.invoke(constructorDefinition, param1, param2);
	}
}
