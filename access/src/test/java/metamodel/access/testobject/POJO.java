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

import java.util.List;

/**
 * @author Michael Kroll
 *
 */
public class POJO {

	private int myint;
	private Integer myInteger;

	@SuppressWarnings("rawtypes")
	private List rawList;
	private List<?> wildcardList;

	@SuppressWarnings("unused")
	private long multidecl1, multidecl2;

	public POJO() {
	}

	/**
	 * @return the myint
	 */
	public int getMyint() {
		return myint;
	}

	/**
	 * @param myint the myint to set
	 */
	public void setMyint(final int myint) {
		this.myint = myint;
	}

	/**
	 * @return the myInteger
	 */
	public Integer getMyInteger() {
		return myInteger;
	}

	/**
	 * @param myInteger the myInteger to set
	 */
	public void setMyInteger(final Integer myInteger) {
		this.myInteger = myInteger;
	}

	/**
	 * @return the rawList
	 */
	@SuppressWarnings("rawtypes")
	public List getRawList() {
		return rawList;
	}

	/**
	 * @param rawList the rawList to set
	 */
	@SuppressWarnings("rawtypes")
	public void setRawList(final List rawList) {
		this.rawList = rawList;
	}

	/**
	 * @return the wildcardList
	 */
	public List<?> getWildcardList() {
		return wildcardList;
	}

	/**
	 * @param wildcardList the wildcardList to set
	 */
	public void setWildcardList(final List<?> wildcardList) {
		this.wildcardList = wildcardList;
	}

	public void methodWithPrimitiveArray(final int[] params) {
	}

	public void methodWithObjectArray(final Long[] params) {
	}
}
