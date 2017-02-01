[![Build Status](https://travis-ci.org/madprogger/metamodel.svg?branch=master)](https://travis-ci.org/madprogger/metamodel)

Meta models for POJOs
====================

This tooling enables you to generate meta model classes of standard POJOs. These meta model classes can then be used to reflectively access the data of your POJO-instances in a type safe way.

What for?
=========

One word: testing. There are certain circumstances, where you want to change/read values of objects that simply do not have any setters/getters for these values. For example:

- Manually set fields that are normally set by a dependency-injection framework, but you don't want to start the whole container just for testing one small class.
- Checking internal state of objects without the need to add getters only for testing. Beware: this type of access points at bad code/design.
- Testing legacy code while not being allowed to alter it

Using reflection in productive/non-testing code is not really the way it's meant to be done, although this tooling may also be used for this type of scenario.

So do we really need meta models for reflective class field manipulation?
One may think simply writing  

	object.getClass().getDeclaredField("fieldName").set(object, newValue);
is all one has to do, with absolutely no need for a meta model.

Well...

- Think of type safety, above code would throw an IllegalArgumentException if `newValue` is of wrong type... during runtime.
- Think of inheritance: what if fieldName is declared in one of the super classes, would this still be a one-liner?
- A type and its super type declare a field with the same name. Are you sure you manipulate only the correct field?
- Compile-time-check with meta model vs. NoSuchFieldException during runtime
- Code-completion: some typos are (nearly) forever until fixed 


Content
=======

- A generator that builds metamodels from java-source-files (using <https://code.google.com/p/javaparser/> for reading source-files and <https://codemodel.java.net/> for writing the metamodels)
- A maven-build-plugin for seamless integration in, well, maven builds :)
- Utilities that you may use along the generated metamodel to read/write values of fields etc. 

Usage
======

The easiest way to access your classes via a metamodel without having to write the metamodel code yourself is to let a generator do the work for you. This can be done with the maven-plugin which is part of this project: 

- add a dependency to the maven-plugin
```xml
<dependency>
	<groupId>com.github.madprogger.metamodel</groupId>
	<artifactId>metamodel-maven-plugin</artifactId>
	<version>1.0.1</version>
</dependency>
```
- use plugin during build
```xml
<build>
	<plugins>
		<plugin>
			<groupId>com.github.madprogger.metamodel</groupId>
			<artifactId>metamodel-maven-plugin</artifactId>
			<version>1.0.1</version>
			<executions>
				<execution>
					<id>metamodel generation</id>
					<goals>
						<goal>generate-metamodel</goal>
					</goals>
				</execution>
			</executions>
		</plugin>
	</plugins>
</build>
```
- add generated source files to be compiled in compile phase 
```xml
<build>
	<plugins>
		<plugin>
			<groupId>org.codehaus.mojo</groupId>
			<artifactId>build-helper-maven-plugin</artifactId>
			<version>1.9.1</version>
			<executions>
				<execution>
					<id>generate-sources</id>
					<phase>generate-sources</phase>
					<goals>
						<goal>add-source</goal>
					</goals>
					<configuration>
						<sources>
						<source>${project.build.directory}/generated-sources/pojo-metamodel</source>
						</sources>
					</configuration>
				</execution>
			</executions>
		</plugin>
	</plugins>
</build>
```

Now you can use the metamodel classes to access your objects. Use the accessor-library to accomplish this with readable one-liners:
- add dependency to accessor-library to your build
```xml
<dependency>
	<groupId>com.github.madprogger.metamodel</groupId>
	<artifactId>accessor</artifactId>
	<version>1.0.1</version>
</dependency>
```
- start writing code like the following:
```java
Example obj = new Example();
// field access
Accessor.on(obj).field(Example_.value).set(13);
int val = Accessor.on(obj).field(Example_.value).get();

// method access
Long retVal = Accessor.on(obj).method(Example_.innerMethod).invoke("my value");

// constructor access
Example obj2 = Accessor.c(Example_.constructor).invoke("Hello World");
```

That's it, have Fun!
