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

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import metamodel.access.testobject.POJO;
import metamodel.access.testobject.POJO_;
import metamodel.access.testobject.SubClassWithSameFieldName;
import metamodel.access.testobject.SubClassWithSameFieldName_;
import metamodel.method.Method1;
import metamodel.method.impl.Method1Impl;

import org.junit.Test;

/**
 * Tests AccessorUtil.
 *
 * @author Michael Kroll
 */
public class AccessorUtilTest {

	@Test
	public void testPOJOSetterGetter() throws Exception {
		final POJO pojo = new POJO();
		pojo.setMyint(42);
		pojo.setMyInteger(23);

		assertEquals(new Integer(42), AccessorUtil.get(pojo, POJO_.myint));
		assertEquals(new Integer(23), AccessorUtil.get(pojo, POJO_.myInteger));

		AccessorUtil.set(pojo, POJO_.myint, 13);
		AccessorUtil.set(pojo, POJO_.myInteger, 7);

		assertEquals(13, pojo.getMyint());
		assertEquals(Integer.valueOf(7), pojo.getMyInteger());
	}

	@Test
	public void testPOJOWildcardGetterSetter() throws Exception {
		final POJO pojo = new POJO();
		pojo.setWildcardList(Arrays.asList(new String("Charly!"), new Integer(42)));

		assertEquals(Arrays.asList(new String("Charly!"), new Integer(42)), AccessorUtil.get(pojo, POJO_.wildcardList));

		AccessorUtil.set(pojo, POJO_.wildcardList, Arrays.asList(new String("Nope!"), new Integer(7)));

		assertEquals(Arrays.asList(new String("Nope!"), new Integer(7)), pojo.getWildcardList());
	}

	@Test
	public void testSubClassBaseClassGetterSetter() throws Exception {
		final SubClassWithSameFieldName pojo = new SubClassWithSameFieldName();

		pojo.setMyInteger(42);
		assertEquals(new Integer(42), AccessorUtil.get(pojo, POJO_.myInteger));

		AccessorUtil.set(pojo, POJO_.myInteger, 7);
		assertEquals(Integer.valueOf(7), pojo.getMyInteger());

		AccessorUtil.set(pojo, SubClassWithSameFieldName_.myInteger, 13);
		assertEquals(Integer.valueOf(13), pojo.getMyInteger());

		pojo.setMyInteger(42);
		assertEquals(new Integer(42), AccessorUtil.get(pojo, SubClassWithSameFieldName_.myInteger));
	}

	@Test
	public void testSubClassHasSameFieldNameAsBaseClassGetterSetter() throws Exception {
		final SubClassWithSameFieldName pojo = new SubClassWithSameFieldName();

		pojo.setMyint(42);
		pojo.sub_setMyint(23);
		assertEquals(new Integer(42), AccessorUtil.get(pojo, POJO_.myint));
		assertEquals(new Integer(23), AccessorUtil.get(pojo, SubClassWithSameFieldName_.myint));

		AccessorUtil.set(pojo, POJO_.myint, 7);
		AccessorUtil.set(pojo, SubClassWithSameFieldName_.myint, 13);
		assertEquals(7, pojo.getMyint());
		assertEquals(13, pojo.sub_getMyint());
	}

	public static class Target {
		public void meth(final String text) {
			System.out.println(text);
		}
	}

	public static class Target_ {
		public static Method1<Target, Void, String> meth = new Method1Impl<>("meth", Target.class, String.class);
	}

	@Test
	public void testName() throws Exception {
		AccessorUtil.on(new Target()).method(Target_.meth).invoke("hello world!!!");
	}
}
