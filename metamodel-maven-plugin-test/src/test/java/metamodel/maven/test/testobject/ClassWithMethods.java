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
package metamodel.maven.test.testobject;

import java.util.List;
import java.util.Map;

/**
 * @author Michael Kroll
 *
 */
@SuppressWarnings("unused")
public class ClassWithMethods {

	public static String staticMethod() {
		return "";
	}

	/** This is the comment to a void method. */
	private void voidMethod() {
	}

	/**
	 * This is the comment to a method with result.
	 *
	 * @return true or false
	 */
	private boolean boolPrimitiveMethod() {
		return true;
	}

	private Integer integerMethod() {
		return 1;
	}

	private void duplicate(final String val) {
	}

	private void duplicate(final Integer val) {
	}

	private void duplicate(final Double val) {
	}

	private void duplicate(final List<Double> val) {
	}

	private void duplicate(final Integer val, final List<List<String>> param) {
	}

	private void duplicate(final Integer val, final List<List<String>> param,
	        final Map<List<String>, List<Boolean>> third) {
	}

	private int m1() {
		return 0;
	}

	private int m1(final String p1) {
		return 1;
	}

	private int m1(final String p1, final String p2) {
		return 2;
	}

	private int m1(final String p1, final String p2, final String p3) {
		return 3;
	}

	private int m1(final String p1, final String p2, final String p3, final String p4) {
		return 4;
	}

	private int m1(final String p1, final String p2, final String p3, final String p4, final String p5) {
		return 5;
	}

	private int m1(final String p1, final String p2, final String p3, final String p4, final String p5,
	        final String p6) {
		return 6;
	}

	private int m1(final String p1, final String p2, final String p3, final String p4, final String p5,
	        final String p6, final String p70) {
		return 7;
	}

	private int m1(final String p1, final String p2, final String p3, final String p4, final String p5,
	        final String p6, final String p7, final String p8) {
		return 8;
	}

	private int m1(final String p1, final String p2, final String p3, final String p4, final String p5,
	        final String p6, final String p7, final String p8, final String p9) {
		return 9;
	}

	private int m1(final String p1, final String p2, final String p3, final String p4, final String p5,
	        final String p6, final String p7, final String p8, final String p9, final String p10) {
		return 10;
	}

	private int m1(final String p1, final String p2, final String p3, final String p4, final String p5,
	        final String p6, final String p7, final String p8, final String p9, final String p10, final String p11) {
		return 11;
	}

	private int m1(final String p1, final String p2, final String p3, final String p4, final String p5,
	        final String p6, final String p7, final String p8, final String p9, final String p10, final String p11,
	        final String p12) {
		return 12;
	}

	private int m1(final String p1, final String p2, final String p3, final String p4, final String p5,
	        final String p6, final String p7, final String p8, final String p9, final String p10, final String p11,
	        final String p12, final String p13) {
		return 13;
	}

	private int m1(final String p1, final String p2, final String p3, final String p4, final String p5,
	        final String p6, final String p7, final String p8, final String p9, final String p10, final String p11,
	        final String p12, final String p13, final String p14) {
		return 14;
	}

	private int m1(final String p1, final String p2, final String p3, final String p4, final String p5,
	        final String p6, final String p7, final String p8, final String p9, final String p10, final String p11,
	        final String p12, final String p13, final String p14, final String p15) {
		return 15;
	}

	private int m1(final String p1, final String p2, final String p3, final String p4, final String p5,
	        final String p6, final String p7, final String p8, final String p9, final String p10, final String p11,
	        final String p12, final String p13, final String p14, final String p15, final String p16) {
		return 16;
	}
}
