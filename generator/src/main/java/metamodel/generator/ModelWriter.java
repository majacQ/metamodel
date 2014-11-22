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
package metamodel.generator;

import java.io.File;
import java.io.IOException;

import com.sun.codemodel.JCodeModel;

/**
 * Writes generated metamodel to specified folder.
 *
 * @author madprogger
 * @see ModelFromClassBuilder
 */
public class ModelWriter {

	/**
	 * Writes generated metamodel as source files to specified folder.
	 *
	 * @param codeModel metamodel
	 * @param targetDir target directory. Will be created if not existent.
	 * @throws IOException error writing source-files
	 */
	public void write(final JCodeModel codeModel, final File targetDir) throws IOException {
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}
		codeModel.build(targetDir);
	}
}
