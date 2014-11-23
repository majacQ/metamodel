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
package metamodel.generator.converter;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JInvocation;

/**
 * @author madprogger
 */
public interface FieldCoverter {

	/** helper for narrowing generics using diamond-operator &lt;&gt;. */
	public static final JClass[] DIAMOND = new JClass[0];

	/**
	 * Convert a field definition.
	 *
	 * @param codeModel JCodeModel instance
	 * @param baseType type of class that defines the field
	 * @param convertedType type of field
	 * @param fieldName name of field
	 * @return ConversionResult
	 */
	FieldDefinition convert(JCodeModel codeModel, JClass baseType, JClass convertedType, String fieldName);

	/**
	 * Get the class that can be converterted.
	 * <p>
	 * eg. 'Collection' -&gt; the converter is called for fields that are defined as Collection or any subclass of
	 * Collection.
	 *
	 * @return Class
	 */
	Class<?> getTargetClass();

	/**
	 * Wrapper for conversion result.
	 * <p>
	 * Holds the type and the initialization for a field variable.
	 *
	 * @author madprogger
	 *
	 */
	public static class FieldDefinition {
		final JClass fieldClass;
		final JInvocation fieldInit;

		/**
		 * @param fieldClass
		 * @param fieldInit
		 */
		public FieldDefinition(final JClass fieldClass, final JInvocation fieldInit) {
			this.fieldClass = fieldClass;
			this.fieldInit = fieldInit;
		}

		/**
		 * @return the fieldClass
		 */
		public JClass getFieldClass() {
			return fieldClass;
		}

		/**
		 * @return the fieldInit
		 */
		public JInvocation getFieldInit() {
			return fieldInit;
		}
	}
}
