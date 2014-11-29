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

import metamodel.method.AbstractMethod;

/**
 * Base implementation for all method-definitions.
 * <p>
 * For Methods with return type {@code void}, the type parameter RT is {@link Void}.
 * <p>
 * By convention, each subclass/interface should have the same type arguments at the same positions: &lt;BASE, RT [,
 * ...]&gt;.
 *
 * @author Michael Kroll
 * @param <BASE> type of class that declares the method
 * @param <RT> return type
 */
public abstract class AbstractMethodImpl<BASE, RT> implements AbstractMethod<BASE, RT> {

	private final String name;
	private final Class<BASE> declaringClass;
	private final Class<?>[] parameterClasses;

	/**
	 * Constructor.
	 *
	 * @param name of the field
	 * @param declaringClass class that declares the field
	 */
	public AbstractMethodImpl(final String name, final Class<BASE> declaringClass, final Class<?>... parameterClasses) {
		this.name = name;
		this.declaringClass = declaringClass;
		this.parameterClasses = parameterClasses;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Class<BASE> getDeclaringClass() {
		return declaringClass;
	}

	@Override
	public Class<?>[] getParameterClasses() {
		return parameterClasses;
	}
}
