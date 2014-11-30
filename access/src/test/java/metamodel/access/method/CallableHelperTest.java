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

import static org.junit.Assert.assertEquals;
import metamodel.access.Accessor;
import metamodel.method.Method0;
import metamodel.method.Method1;
import metamodel.method.impl.Method0Impl;
import metamodel.method.impl.Method1Impl;

import org.junit.Test;

/**
 * @author Michael Kroll
 *
 */
public class CallableHelperTest {

	public static class Target {
		private int value;

		@SuppressWarnings("unused")
		private void meth(final String text) {
			System.out.println(text);
		}

		public int getValue() {
			return value;
		}

		public void setValue(final int value) {
			this.value = value;
		}
	}

	public static class Target_ {
		public static Method1<Target, Void, String> meth = new Method1Impl<>("meth", Target.class, String.class);
		public static Method0<Target, Integer> getValue = new Method0Impl<>("getValue", Target.class);
		public static Method1<Target, Void, Integer> setValue = new Method1Impl<>("setValue", Target.class, int.class);
	}

	@Test
	public void testName() throws Exception {
		final Target target = new Target();
		assertEquals(null, Accessor.on(target).method(Target_.meth).invoke("hello world!!!"));
		assertEquals(null, Accessor.on(target).method(Target_.setValue).invoke(42));
		assertEquals(new Integer(42), Accessor.on(target).method(Target_.getValue).invoke());
	}
}
