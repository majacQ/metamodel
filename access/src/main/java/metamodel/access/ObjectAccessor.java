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
package metamodel.access;

import metamodel.access.method.Callable0;
import metamodel.access.method.Callable1;
import metamodel.access.method.Callable2;
import metamodel.method.Method0;
import metamodel.method.Method1;
import metamodel.method.Method2;

public class ObjectAccessor<BASE> {

	private final BASE object;

	public ObjectAccessor(final BASE object) {
		this.object = object;
	}

	public <RT> Callable0<BASE, RT> method(final Method0<BASE, RT> methodDefinition) {
		return new Callable0<>(object, methodDefinition);
	}

	public <RT, P1, P2> Callable1<BASE, RT, P1> method(final Method1<BASE, RT, P1> methodDefinition) {
		return new Callable1<>(object, methodDefinition);
	}

	public <RT, P1, P2> Callable2<BASE, RT, P1, P2> method(final Method2<BASE, RT, P1, P2> methodDefinition) {
		return new Callable2<>(object, methodDefinition);
	}
}