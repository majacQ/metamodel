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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import metamodel.access.Accessor;
import metamodel.constructor.Constructor2;
import metamodel.constructor.impl.Constructor2Impl;

import org.junit.Test;

/**
 * @author Michael Kroll
 *
 */
public class ConstructorHelperTest {

	public static class Target {
		private final int value;
		private final String text;

		private Target(final int value, final String text) {
			this.value = value;
			this.text = text;
		}

		public String getText() {
			return text;
		}

		public int getValue() {
			return value;
		}
	}

	public static class Target_ {
		public static Constructor2<Target, Integer, String> constructor =
		        new Constructor2Impl<>(Target.class, int.class, String.class);
	}

	@Test
	public void testName() throws Exception {
		final Target target = Accessor.c(Target_.constructor).invoke(42, "Hello World");
		assertNotNull(target);
		assertEquals(42, target.getValue());
		assertEquals("Hello World", target.getText());
	}
}
