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
package metamodel.maven;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import metamodel.generator.ModelFromSourceBuilder;
import metamodel.generator.ModelWriter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.sun.codemodel.JCodeModel;

/**
 * Baseclass for all java-&gt;metamodel generator-mojos.
 * 
 * @author Michael Kroll
 */
public abstract class BaseGeneratorMojo extends AbstractMojo {

	public void execute(final String[] sources, final String targetDir) throws MojoExecutionException,
	        MojoFailureException {
		try {
			getLog().info("generateDir=" + targetDir);
			getLog().info("sources=" + Arrays.toString(sources));

			final List<File> files = new ArrayList<>();
			for (final String compileSourceRoot : sources) {
				files.addAll(findAllFiles(new File(compileSourceRoot)));
			}
			getLog().debug("found files to process:");
			for (final File file : files) {
				getLog().debug(file.toString());
			}

			final JCodeModel model = new ModelFromSourceBuilder().buildCodeModel(new HashSet<>(files));
			new ModelWriter().write(model, new File(targetDir));

		} catch (final Throwable e) {
			e.printStackTrace();
		}
	}

	private List<File> findAllFiles(final File entry) {
		final List<File> result = new ArrayList<>();
		if (entry.isDirectory()) {
			for (final File dirEntry : entry.listFiles()) {
				result.addAll(findAllFiles(dirEntry));
			}
		} else if (entry.getName().endsWith(".java")) {
			result.add(entry);
		}
		return result;
	}
}
