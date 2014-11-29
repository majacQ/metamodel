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
package metamodel.access.testobject;

/**
 * This is a subclass of POJO that declares a field with the same name as the base class.
 *
 * @author Michael Kroll
 */
public class SubClassWithSameFieldName extends POJO {

	private int myint;

	private String subString;

	/**
	 * @return the myint
	 */
	public int sub_getMyint() {
		return myint;
	}

	/**
	 * @param myint the myint to set
	 */
	public void sub_setMyint(final int myint) {
		this.myint = myint;
	}

	/**
	 * @return the subString
	 */
	public String getSubString() {
		return subString;
	}

	/**
	 * @param subString the subString to set
	 */
	public void setSubString(final String subString) {
		this.subString = subString;
	}
}
