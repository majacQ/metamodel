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


		assertEquals(new Integer(42), AccessorUtil.on(pojo).field(POJO_.myint).get());
		assertEquals(new Integer(23), AccessorUtil.on(pojo).field(POJO_.myInteger).get());

		AccessorUtil.on(pojo).field(POJO_.myint).set(13);
		AccessorUtil.on(pojo).field(POJO_.myInteger).set(7);

		assertEquals(13, pojo.getMyint());
		assertEquals(Integer.valueOf(7), pojo.getMyInteger());
	}

	@Test
	public void testPOJOWildcardGetterSetter() throws Exception {
		final POJO pojo = new POJO();
		pojo.setWildcardList(Arrays.asList(new String("Charly!"), new Integer(42)));

		assertEquals(Arrays.asList(new String("Charly!"), new Integer(42)),
		        AccessorUtil.on(pojo).field(POJO_.wildcardList).get());

		AccessorUtil.on(pojo).field(POJO_.wildcardList).set(Arrays.asList(new String("Nope!"), new Integer(7)));

		assertEquals(Arrays.asList(new String("Nope!"), new Integer(7)), pojo.getWildcardList());
	}

	@Test
	public void testSubClassBaseClassGetterSetter() throws Exception {
		final SubClassWithSameFieldName pojo = new SubClassWithSameFieldName();

		pojo.setMyInteger(42);
		assertEquals(new Integer(42), AccessorUtil.on(pojo).field(SubClassWithSameFieldName_.myInteger).get());

		AccessorUtil.on(pojo).field(POJO_.myInteger).set(7);
		assertEquals(Integer.valueOf(7), pojo.getMyInteger());

		AccessorUtil.on(pojo).field(SubClassWithSameFieldName_.myInteger).set(13);
		assertEquals(Integer.valueOf(13), pojo.getMyInteger());

		pojo.setMyInteger(42);
		assertEquals(new Integer(42), AccessorUtil.on(pojo).field(SubClassWithSameFieldName_.myInteger).get());
	}

	@Test
	public void testSubClassHasSameFieldNameAsBaseClassGetterSetter() throws Exception {
		final SubClassWithSameFieldName pojo = new SubClassWithSameFieldName();

		pojo.setMyint(42);
		pojo.sub_setMyint(23);
		assertEquals(new Integer(42), AccessorUtil.on(pojo).field(POJO_.myint).get());
		assertEquals(new Integer(23), AccessorUtil.on(pojo).field(SubClassWithSameFieldName_.myint).get());

		AccessorUtil.on(pojo).field(POJO_.myint).set(7);
		AccessorUtil.on(pojo).field(SubClassWithSameFieldName_.myint).set(13);
		assertEquals(7, pojo.getMyint());
		assertEquals(13, pojo.sub_getMyint());
	}

	@Test
	public void testFieldAccessInClassHierarchy() throws Exception {
		final SubClassWithSameFieldName sub = new SubClassWithSameFieldName();
		final POJO pojo = new POJO();

		AccessorUtil.on(pojo).field(POJO_.myInteger).set(5);
		// implicit downcast should not be allowed:
		// AccessorUtil.on(pojo).field(SubClassWithSameFieldName_.subString).set("hello world");

		AccessorUtil.on(sub).field(POJO_.myInteger).set(5);
		AccessorUtil.on(sub).field(SubClassWithSameFieldName_.subString).set("hello world");
	}

	@Test
	public void testMethodAccessInClassHierarchy() throws Exception {
		final SubClassWithSameFieldName sub = new SubClassWithSameFieldName();
		final POJO pojo = new POJO();

		AccessorUtil.on(pojo).method(POJO_.setMyint).invoke(5);
		// implicit downcast should not be allowed:
		// AccessorUtil.on(pojo).method(SubClassWithSameFieldName_.sub_setMyint).invoke(5);

		AccessorUtil.on(sub).method(POJO_.setMyint).invoke(5);
		AccessorUtil.on(sub).method(SubClassWithSameFieldName_.sub_setMyint).invoke(5);
	}

	@Test
	public void testMethodWithObjectArray() throws Exception {
		final POJO pojo = new POJO();
		AccessorUtil.on(pojo).method(POJO_.methodWithObjectArray).invoke(new Long[] { 5L, 7L });
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMethodWithPrimitiveArray() throws Exception {
		final POJO pojo = new POJO();
		AccessorUtil.on(pojo).method(POJO_.methodWithPrimitiveArray).invoke(new Integer[] { 5, 7 });
	}
}
