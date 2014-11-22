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
package org.example.test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


@SuppressWarnings("unused")
public class POJOTestClass {
	// static
	private static String staticField;

	// primitive types
	private boolean boolPrim;

	// array types
	private boolean[] boolArray;
	private boolean[][] boolArrayArray;
	private boolean[][][] boolArrayArrayArray;

	// simple types
	private String string;
	private Boolean bool;

	// plural types
	private Collection<Boolean> collection;
	private List<Boolean> list;
	private Set<Boolean> set;
	private Map<String, Boolean> map;

	// plural types of arrays
	private Collection<Boolean[]> collectionOfArray;
	private List<Boolean[]> listOfArray;
	private Set<Boolean[]> setOfArray;
	private Map<String[], Boolean[]> mapOfArray;

	// multi-plural types
	private Collection<List<Boolean>> collectionOfList;
	private List<Set<Boolean>> listOfSet;
	private Set<Map<String, Boolean>> setOfMap;
	private Map<Collection<String>, Collection<Boolean>> mapOfCollection;

	// wildcard plural types
	private Collection<?> wildcardCollection;
	private List<?> wildcardList;
	private Set<?> wildcardSet;
	private Map<String, ?> wildcardMap1;
	private Map<?, Boolean> wildcardMap2;
	private Map<?, ?> wildcardMap3;

	// wildcard extends plural types
	private Collection<? extends POJOTestClass> wildcardExtendsCollection;
	private List<? extends POJOTestClass> wildcardExtendsList;
	private Set<? extends POJOTestClass> wildcardExtendsSet;
	private Map<String, ? extends POJOTestClass> wildcardExtendsMap1;
	private Map<? extends POJOTestClass, Boolean> wildcardExtendsMap2;
	private Map<? extends POJOTestClass, ? extends POJOTestClass> wildcardExtendsMap3;

	// wildcard extends generic in plural types
	private Collection<? extends List<String>> wildcardExtendsGenericCollection;
	private List<? extends List<String>> wildcardExtendsGenericList;
	private Set<? extends List<String>> wildcardExtendsGenericSet;
	private Map<String, ? extends List<String>> wildcardExtendsGenericMap1;
	private Map<? extends List<String>, Boolean> wildcardExtendsGenericMap2;
	private Map<? extends List<String>, ? extends List<? extends POJOTestClass>> wildcardExtendsGenericMap3;

	// raw plural types
	@SuppressWarnings("rawtypes")
	private Collection rawCollection;
	@SuppressWarnings("rawtypes")
	private List rawList;
	@SuppressWarnings("rawtypes")
	private Set rawSet;
	@SuppressWarnings("rawtypes")
	private Map rawMap;

    // custom types
	private POJOTestClass2<List<Set<String>>> custom;
	private POJOTestClass2<List<Set<String>>>[] customArray;

	// crazy definition
	private Map<? extends List<String[]>[], ? extends List<? extends POJOTestClass[]>[]>[] customWildcardExtendsGenericMapWithArrays;

}