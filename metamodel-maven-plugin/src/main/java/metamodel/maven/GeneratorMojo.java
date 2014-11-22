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
package metamodel.maven;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import metamodel.generator.ModelFromSourceBuilder;
import metamodel.generator.ModelWriter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.sun.codemodel.JCodeModel;

/**
 * @author madprogger
 */
@Mojo(name = "generate-metamodel", defaultPhase = LifecyclePhase.PROCESS_CLASSES)
public class GeneratorMojo extends AbstractMojo {

	@Parameter(defaultValue = "${project.build.outputDirectory}")
	private String classFileDir;

	@Parameter(defaultValue = "${project.build.directory}/generated-metamodel")
	private String generateDir;

	/**
	 * The source directories containing the sources to be processed.
	 */
	@Parameter(property = "project.compileSourceRoots", readonly = true, required = true)
	private List<String> compileSourceRoots;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			getLog().info("Hello, world.");
			getLog().info("classFileDir=" + classFileDir);
			getLog().info("generateDir=" + generateDir);

			final List<File> files = new ArrayList<>();
			for (final String compileSourceRoot : compileSourceRoots) {
				files.addAll(findAllFiles(new File(compileSourceRoot)));
			}
			getLog().debug("found files to process:");
			for (final File file : files) {
				getLog().debug(file.toString());
			}

			final JCodeModel model = new ModelFromSourceBuilder().buildCodeModel(new HashSet<>(files));
			new ModelWriter().write(model, new File(generateDir));

		} catch (final Throwable e) {
			e.printStackTrace();
		}
	}

	private List<File> findAllFiles(final File dir) {
		final List<File> result = new ArrayList<>();
		for (final File entry : dir.listFiles()) {
			if (entry.isDirectory()) {
				result.addAll(findAllFiles(entry));
			} else if (entry.getName().endsWith(".java")) {
				result.add(entry);
			}
		}
		return result;
	}

}
