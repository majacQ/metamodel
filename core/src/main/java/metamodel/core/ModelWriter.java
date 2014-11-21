package metamodel.core;

import java.io.File;
import java.io.IOException;

import com.sun.codemodel.JCodeModel;

public class ModelWriter {

	public void write(final JCodeModel codeModel, final File targetDir) throws IOException {
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}
		codeModel.build(targetDir);
	}
}
