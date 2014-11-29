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
package metamodel.method;

/**
 * Base interface for all method-definitions.
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
public interface AbstractMethod<BASE, RT> {
	/**
	 * Get the name of the field.
	 *
	 * @return the name of the field
	 */
	String getName();

	/**
	 * Get class that declares the field.
	 *
	 * @return class that declares the field
	 */
	Class<BASE> getDeclaringClass();

	/**
	 * Get classes of defined Parameters.
	 * 
	 * @return classes of defined Parameters, in order of their declaration
	 */
	Class<?>[] getParameterClasses();
}
