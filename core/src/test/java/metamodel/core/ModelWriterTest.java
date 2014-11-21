package metamodel.core;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.example.test.ClassWithInnerClasses;
import org.example.test.ClassWithInnerClasses2;
import org.example.test.ClassWithInnerClasses3;
import org.example.test.POJOTestClass;
import org.example.test.POJOTestClass2;
import org.example.test.SubTestClass;
import org.example.test.SubTestClass2;
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
		final JCodeModel codeModel = new ModelBuilder().buildCodeModel(classes);
		new ModelWriter().write(codeModel, new File("target/generated-pojo-metamodel"));
	}

	@Test
	public void testSubClassModelGeneration() throws Exception {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(SubTestClass.class);
		classes.add(POJOTestClass.class);
		classes.add(POJOTestClass2.class);
		final JCodeModel codeModel = new ModelBuilder().buildCodeModel(classes);
		new ModelWriter().write(codeModel, new File("target/generated-pojo-metamodel"));
	}

	@Test
	public void testSubClassWithMissingSuperClassModelGeneration() throws Exception {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(SubTestClass2.class);
		final JCodeModel codeModel = new ModelBuilder().buildCodeModel(classes);
		new ModelWriter().write(codeModel, new File("target/generated-pojo-metamodel"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExplicitInnerClassGeneration_NotAllowed() throws Exception {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(ClassWithInnerClasses.InnerClass.class);
		new ModelBuilder().buildCodeModel(classes);
	}

	@Test
	public void testImplicitInnerClassGeneration() throws Exception {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(ClassWithInnerClasses3.class);
		classes.add(ClassWithInnerClasses2.class);
		final JCodeModel codeModel = new ModelBuilder().buildCodeModel(classes);
		new ModelWriter().write(codeModel, new File("target/generated-pojo-metamodel"));
	}
}
