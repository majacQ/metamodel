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

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.example.test.POJOTestClass;
import org.junit.Test;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;

/**
 * Unit test for ModelFromSourceBuilder.
 */
public class ModelFromSourceBuilderTest {

	@Test
	public void testPOJOModelGeneration() throws Exception {
		final Set<File> classes = new HashSet<>();
		classes.add(new File("src/test/java/ClassInDefaultPackage.java"));

		classes.add(new File("src/test/java/org/example/test/POJOTestClass.java"));
		classes.add(new File("src/test/java/org/example/test/POJOTestClass2.java"));

		classes.add(new File("src/test/java/org/example/test/SubTestClass.java"));

		classes.add(new File("src/test/java/org/example/test/ClassWithInnerClasses.java"));
		classes.add(new File("src/test/java/org/example/test/ClassWithInnerClasses2.java"));
		classes.add(new File("src/test/java/org/example/test/ClassWithInnerClasses3.java"));

		final JCodeModel codeModel = new ModelFromSourceBuilder().buildCodeModel(classes);
		new ModelWriter().write(codeModel, new File("target/generated-pojo-metamodel"));
		final JDefinedClass metaClass = codeModel._getClass(POJOTestClass.class.getName() + "_");

		assertThat(metaClass.fields().keySet(), hasItem("string"));
		assertThat(metaClass.fields().keySet(), hasItem("bool"));
		assertThat(metaClass.fields().keySet(), hasItem("boolPrim"));
		assertThat(metaClass.fields().keySet(), hasItem("collection"));
		assertThat(metaClass.fields().keySet(), hasItem("list"));
		assertThat(metaClass.fields().keySet(), hasItem("set"));
		assertThat(metaClass.fields().keySet(), hasItem("map"));
		assertThat(metaClass.fields().keySet(), not(hasItem("staticField")));
	}
}
