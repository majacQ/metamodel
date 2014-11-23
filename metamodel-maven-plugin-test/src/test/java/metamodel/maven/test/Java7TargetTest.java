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
package metamodel.maven.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import metamodel.field.CollectionField;
import metamodel.field.MapField;

import org.junit.Test;

/**
 * Ensures that Java7Target_ was created
 *
 * @author madprogger
 */
public class Java7TargetTest {

	@Test
	public void testClassExists() throws Exception {
		Class.forName("metamodel.maven.test.Java7Target_");
	}

	@Test
	public void testMemberExist() throws Exception {
		final Class<?> clazz = Class.forName("metamodel.maven.test.Java7Target_");
		clazz.getDeclaredField("diamondList");
		clazz.getDeclaredField("diamondMap");
	}

	@Test
	public void testMembersHaveCorrectTypes() throws Exception {
		final Class<?> clazz = Class.forName("metamodel.maven.test.Java7Target_");
		assertEquals(CollectionField.class, clazz.getDeclaredField("diamondList").getType());
		assertEquals(MapField.class, clazz.getDeclaredField("diamondMap").getType());

		for (final Field sourceField : Java7Target.class.getDeclaredFields()) {
			final Field targetField = clazz.getDeclaredField(sourceField.getName());
			assertTrue(targetField.getGenericType() instanceof ParameterizedType);
			final ParameterizedType paramType = (ParameterizedType) targetField.getGenericType();
			assertTrue(paramType.getActualTypeArguments().length >= 2);
			assertEquals(Java7Target.class, paramType.getActualTypeArguments()[0]);
			assertTrue(paramType.getActualTypeArguments()[1] instanceof ParameterizedType);
			final ParameterizedType arg1 = (ParameterizedType) paramType.getActualTypeArguments()[1];
			assertEquals(sourceField.getType(), arg1.getRawType());
			assertEquals(sourceField.getGenericType(), arg1);
		}
	}
}
