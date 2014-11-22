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

import metamodel.access.target.POJO;
import metamodel.access.target.POJO_;

import org.junit.Test;

/**
 * Tests AccessorUtil.
 *
 * @author madprogger
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
}
