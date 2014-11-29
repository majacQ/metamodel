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

import metamodel.access.field.FieldAccessor;
import metamodel.access.method.Callable0;
import metamodel.access.method.Callable1;
import metamodel.access.method.Callable10;
import metamodel.access.method.Callable11;
import metamodel.access.method.Callable12;
import metamodel.access.method.Callable13;
import metamodel.access.method.Callable14;
import metamodel.access.method.Callable15;
import metamodel.access.method.Callable16;
import metamodel.access.method.Callable2;
import metamodel.access.method.Callable3;
import metamodel.access.method.Callable4;
import metamodel.access.method.Callable5;
import metamodel.access.method.Callable6;
import metamodel.access.method.Callable7;
import metamodel.access.method.Callable8;
import metamodel.access.method.Callable9;
import metamodel.field.AbstractField;
import metamodel.method.Method0;
import metamodel.method.Method1;
import metamodel.method.Method10;
import metamodel.method.Method11;
import metamodel.method.Method12;
import metamodel.method.Method13;
import metamodel.method.Method14;
import metamodel.method.Method15;
import metamodel.method.Method16;
import metamodel.method.Method2;
import metamodel.method.Method3;
import metamodel.method.Method4;
import metamodel.method.Method5;
import metamodel.method.Method6;
import metamodel.method.Method7;
import metamodel.method.Method8;
import metamodel.method.Method9;

/**
 * Accessor methods for object instances.
 *
 * @author Michael Kroll
 * @param <BASE> type of object to be accessed
 */
public class ObjectAccessor<BASE> {

	/** object to be accessed. */
	private final BASE object;

	protected ObjectAccessor(final BASE object) {
		this.object = object;
	}

	public <TYPE> FieldAccessor<BASE, TYPE> field(final AbstractField<? super BASE, TYPE> fieldDefinition) {
		return new FieldAccessor<>(object, fieldDefinition);
	}

	public <RT> Callable0<BASE, RT> method(final Method0<? super BASE, RT> methodDefinition) {
		return new Callable0<>(object, methodDefinition);
	}

	public <RT, P1> Callable1<BASE, RT, P1> method(final Method1<? super BASE, RT, P1> methodDefinition) {
		return new Callable1<>(object, methodDefinition);
	}

	public <RT, P1, P2> Callable2<BASE, RT, P1, P2> method(final Method2<? super BASE, RT, P1, P2> methodDefinition) {
		return new Callable2<>(object, methodDefinition);
	}

	public <RT, P1, P2, P3> Callable3<BASE, RT, P1, P2, P3> method(
	        final Method3<? super BASE, RT, P1, P2, P3> methodDefinition) {
		return new Callable3<>(object, methodDefinition);
	}

	public <RT, P1, P2, P3, P4> Callable4<BASE, RT, P1, P2, P3, P4> method(
	        final Method4<? super BASE, RT, P1, P2, P3, P4> methodDefinition) {
		return new Callable4<>(object, methodDefinition);
	}

	public <RT, P1, P2, P3, P4, P5> Callable5<BASE, RT, P1, P2, P3, P4, P5> method(
	        final Method5<? super BASE, RT, P1, P2, P3, P4, P5> methodDefinition) {
		return new Callable5<>(object, methodDefinition);
	}

	public <RT, P1, P2, P3, P4, P5, P6> Callable6<BASE, RT, P1, P2, P3, P4, P5, P6> method(
	        final Method6<? super BASE, RT, P1, P2, P3, P4, P5, P6> methodDefinition) {
		return new Callable6<>(object, methodDefinition);
	}

	public <RT, P1, P2, P3, P4, P5, P6, P7> Callable7<BASE, RT, P1, P2, P3, P4, P5, P6, P7> method(
	        final Method7<? super BASE, RT, P1, P2, P3, P4, P5, P6, P7> methodDefinition) {
		return new Callable7<>(object, methodDefinition);
	}

	public <RT, P1, P2, P3, P4, P5, P6, P7, P8> Callable8<BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8> method(
	        final Method8<? super BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8> methodDefinition) {
		return new Callable8<>(object, methodDefinition);
	}

	public <RT, P1, P2, P3, P4, P5, P6, P7, P8, P9> Callable9<BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9> method(
	        final Method9<? super BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9> methodDefinition) {
		return new Callable9<>(object, methodDefinition);
	}

	public <RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> Callable10<BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> method(
	        final Method10<? super BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> methodDefinition) {
		return new Callable10<>(object, methodDefinition);
	}

	public <RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> Callable11<BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> method(
	        final Method11<? super BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> methodDefinition) {
		return new Callable11<>(object, methodDefinition);
	}

	public <RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> Callable12<BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> method(
	        final Method12<? super BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> methodDefinition) {
		return new Callable12<>(object, methodDefinition);
	}

	public <RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> Callable13<BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> method(
	        final Method13<? super BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> methodDefinition) {
		return new Callable13<>(object, methodDefinition);
	}

	public <RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> Callable14<BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> method(
	        final Method14<? super BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> methodDefinition) {
		return new Callable14<>(object, methodDefinition);
	}

	public <RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> Callable15<BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> method(
	        final Method15<? super BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> methodDefinition) {
		return new Callable15<>(object, methodDefinition);
	}

	public <RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16> Callable16<BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16> method(
	        final Method16<? super BASE, RT, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16> methodDefinition) {
		return new Callable16<>(object, methodDefinition);
	}
}
