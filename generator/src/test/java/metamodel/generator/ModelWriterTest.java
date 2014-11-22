/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 madprogger
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
package metamodel.generator;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.example.test.ClassWithAnonymousInnerClass;
import org.example.test.ClassWithInnerClasses;
import org.example.test.ClassWithInnerClasses2;
import org.example.test.ClassWithInnerClasses3;
import org.example.test.POJOTestClass;
import org.example.test.POJOTestClass2;
import org.example.test.SubTestClass;
import org.example.test.SubTestClass2;
import org.example.test.TestAnnotation;
import org.example.test.TestEnum;
import org.example.test.TestInterface;
import org.junit.Test;

import com.sun.codemodel.JCodeModel;

/**
 * Unit test for ModelWriter.
 */
public class ModelWriterTest {

	@Test
	public void testPOJOModelGeneration() throws Exception {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(POJOTestClass.class);
		classes.add(POJOTestClass2.class);
		final JCodeModel codeModel = new ModelFromClassBuilder().buildCodeModel(classes);
		new ModelWriter().write(codeModel, new File("target/generated-pojo-metamodel"));
	}

	@Test
	public void testSubClassModelGeneration() throws Exception {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(SubTestClass.class);
		classes.add(POJOTestClass.class);
		classes.add(POJOTestClass2.class);
		final JCodeModel codeModel = new ModelFromClassBuilder().buildCodeModel(classes);
		new ModelWriter().write(codeModel, new File("target/generated-pojo-metamodel"));
	}

	@Test
	public void testSubClassWithMissingSuperClassModelGeneration() throws Exception {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(SubTestClass2.class);
		final JCodeModel codeModel = new ModelFromClassBuilder().buildCodeModel(classes);
		new ModelWriter().write(codeModel, new File("target/generated-pojo-metamodel"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExplicitInnerClassGeneration_NotAllowed() throws Exception {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(ClassWithInnerClasses.InnerClass.class);
		new ModelFromClassBuilder().buildCodeModel(classes);
	}

	@Test
	public void testImplicitInnerClassGeneration() throws Exception {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(ClassWithInnerClasses3.class);
		classes.add(ClassWithInnerClasses2.class);
		final JCodeModel codeModel = new ModelFromClassBuilder().buildCodeModel(classes);
		new ModelWriter().write(codeModel, new File("target/generated-pojo-metamodel"));
	}

	@Test
	public void testImplicitInnerClassGeneration_NoAnonymousClasses() throws Exception {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(ClassWithAnonymousInnerClass.class);
		final JCodeModel codeModel = new ModelFromClassBuilder().buildCodeModel(classes);
		new ModelWriter().write(codeModel, new File("target/generated-pojo-metamodel"));
	}

	@Test
	public void testEnum() throws Exception {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(TestEnum.class);
		final JCodeModel codeModel = new ModelFromClassBuilder().buildCodeModel(classes);
		new ModelWriter().write(codeModel, new File("target/generated-pojo-metamodel"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInterface() throws Exception {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(TestInterface.class);
		final JCodeModel codeModel = new ModelFromClassBuilder().buildCodeModel(classes);
		new ModelWriter().write(codeModel, new File("target/generated-pojo-metamodel"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAnnotation() throws Exception {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(TestAnnotation.class);
		final JCodeModel codeModel = new ModelFromClassBuilder().buildCodeModel(classes);
		new ModelWriter().write(codeModel, new File("target/generated-pojo-metamodel"));
	}
}
