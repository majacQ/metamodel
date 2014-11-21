package metamodel.core;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.example.test.POJOTestClass;
import org.junit.Test;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;

/**
 * Unit test for simple App.
 */
public class ModelBuilderTest {

	@Test
	public void testPOJOModelGeneration() throws Exception {
		final Set<Class<?>> classes = new HashSet<>();
		classes.add(POJOTestClass.class);
		final JCodeModel codeModel = new ModelBuilder().buildCodeModel(classes);
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
