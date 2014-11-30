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

import metamodel.access.constructor.Constructable0;
import metamodel.access.constructor.Constructable1;
import metamodel.access.constructor.Constructable10;
import metamodel.access.constructor.Constructable11;
import metamodel.access.constructor.Constructable12;
import metamodel.access.constructor.Constructable13;
import metamodel.access.constructor.Constructable14;
import metamodel.access.constructor.Constructable15;
import metamodel.access.constructor.Constructable16;
import metamodel.access.constructor.Constructable2;
import metamodel.access.constructor.Constructable3;
import metamodel.access.constructor.Constructable4;
import metamodel.access.constructor.Constructable5;
import metamodel.access.constructor.Constructable6;
import metamodel.access.constructor.Constructable7;
import metamodel.access.constructor.Constructable8;
import metamodel.access.constructor.Constructable9;
import metamodel.constructor.Constructor0;
import metamodel.constructor.Constructor1;
import metamodel.constructor.Constructor10;
import metamodel.constructor.Constructor11;
import metamodel.constructor.Constructor12;
import metamodel.constructor.Constructor13;
import metamodel.constructor.Constructor14;
import metamodel.constructor.Constructor15;
import metamodel.constructor.Constructor16;
import metamodel.constructor.Constructor2;
import metamodel.constructor.Constructor3;
import metamodel.constructor.Constructor4;
import metamodel.constructor.Constructor5;
import metamodel.constructor.Constructor6;
import metamodel.constructor.Constructor7;
import metamodel.constructor.Constructor8;
import metamodel.constructor.Constructor9;

/**
 * Provides access for fields, methods and constructors.
 *
 * @author Michael Kroll
 * @see #on(Object)
 * @see ObjectAccessor
 */
public class Accessor {

	/**
	 * Create new Object Accessor for object.
	 *
	 * @param object target object which should be accessed
	 * @return ObjectAccessor
	 * @see ObjectAccessor
	 */
	public static <BASE> ObjectAccessor<BASE> on(final BASE object) {
		return new ObjectAccessor<>(object);
	}

	public static <BASE> Constructable0<BASE> c(final Constructor0<BASE> constructorDefinition) {
		return new Constructable0<>(constructorDefinition);
	}

	public static <BASE, P1> Constructable1<BASE, P1> c(final Constructor1<BASE, P1> constructorDefinition) {
		return new Constructable1<>(constructorDefinition);
	}

	public static <BASE, P1, P2> Constructable2<BASE, P1, P2> c(final Constructor2<BASE, P1, P2> constructorDefinition) {
		return new Constructable2<>(constructorDefinition);
	}

	public static <BASE, P1, P2, P3> Constructable3<BASE, P1, P2, P3> c(
	        final Constructor3<BASE, P1, P2, P3> constructorDefinition) {
		return new Constructable3<>(constructorDefinition);
	}

	public static <BASE, P1, P2, P3, P4> Constructable4<BASE, P1, P2, P3, P4> c(
	        final Constructor4<BASE, P1, P2, P3, P4> constructorDefinition) {
		return new Constructable4<>(constructorDefinition);
	}

	public static <BASE, P1, P2, P3, P4, P5> Constructable5<BASE, P1, P2, P3, P4, P5> c(
	        final Constructor5<BASE, P1, P2, P3, P4, P5> constructorDefinition) {
		return new Constructable5<>(constructorDefinition);
	}

	public static <BASE, P1, P2, P3, P4, P5, P6> Constructable6<BASE, P1, P2, P3, P4, P5, P6> c(
	        final Constructor6<BASE, P1, P2, P3, P4, P5, P6> constructorDefinition) {
		return new Constructable6<>(constructorDefinition);
	}

	public static <BASE, P1, P2, P3, P4, P5, P6, P7> Constructable7<BASE, P1, P2, P3, P4, P5, P6, P7> c(
	        final Constructor7<BASE, P1, P2, P3, P4, P5, P6, P7> constructorDefinition) {
		return new Constructable7<>(constructorDefinition);
	}

	public static <BASE, P1, P2, P3, P4, P5, P6, P7, P8> Constructable8<BASE, P1, P2, P3, P4, P5, P6, P7, P8> c(
	        final Constructor8<BASE, P1, P2, P3, P4, P5, P6, P7, P8> constructorDefinition) {
		return new Constructable8<>(constructorDefinition);
	}

	public static <BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9> Constructable9<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9> c(
	        final Constructor9<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9> constructorDefinition) {
		return new Constructable9<>(constructorDefinition);
	}

	public static <BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> Constructable10<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> c(
	        final Constructor10<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> constructorDefinition) {
		return new Constructable10<>(constructorDefinition);
	}

	public static <BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> Constructable11<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> c(
	        final Constructor11<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> constructorDefinition) {
		return new Constructable11<>(constructorDefinition);
	}

	public static <BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> Constructable12<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> c(
	        final Constructor12<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> constructorDefinition) {
		return new Constructable12<>(constructorDefinition);
	}

	public static <BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> Constructable13<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> c(
	        final Constructor13<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> constructorDefinition) {
		return new Constructable13<>(constructorDefinition);
	}

	public static <BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> Constructable14<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> c(
	        final Constructor14<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> constructorDefinition) {
		return new Constructable14<>(constructorDefinition);
	}

	public static <BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> Constructable15<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> c(
	        final Constructor15<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> constructorDefinition) {
		return new Constructable15<>(constructorDefinition);
	}

	public static <BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16> Constructable16<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16> c(
	        final Constructor16<BASE, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16> constructorDefinition) {
		return new Constructable16<>(constructorDefinition);
	}
}