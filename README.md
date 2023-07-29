# CodeChallRunner

*CodeChallRunner* is a simple library that provide a clean environment to run on your machine
- coding challenges
- copetitive programming problems 
- coding interview questions

It offers a clean way to define input and output from recurrent formats (String, Integer, List, Matrix)

## Use

Add this to your Maven configuration 

```xml
  <repositories>
		<repository>
			<id>github-repo</id>
			<name>Github repo</name>
          	<url>https://maven.pkg.github.com/agtonybarletta/codechallrunner</url>
		</repository>
	</repositories>
  <dependencies>
	<dependency>
	  <groupId>it.agtonybarletta.codechallrunner</groupId>
	  <artifactId>codechallrunner</artifactId>
	  <version>X.Y.Z</version>
	</dependency>
  </dependencies>
```

## Usage

Let's assume we want to test a function sum all integers in a list.
Inputs files will have format `inputlistSum.<TESTCASE_NUMBER>.txt` where `<TESTCASE_NUMBER>` is between 0 and N.

```txt
1 2 3 4 5 6
```
Output files will have format
```txt
21
```

To test the function `sumAll` you will create the following runner
```java
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputListSum")
          .addInput(new ListInput<Integer>(new SingleInteger(), " ", "", ""))
          .addTargetFile("targetListSum")
          .addTarget(new SingleInteger())
          .build();
        Boolean result = runner.test(this, "sumAll");
```

## TODO

- [ ] fix loggin 
- [ ] logging test
- [ ] look into auto boxing/unboxing for test function
- [ ] test custom class mapper for single input
- [ ] refactor & clean
- [ ] write readme install and usage
- [ ] write a complete test function that identify the failed test outputing input, output and target
- [ ] check how to call object method from static method
- [ ] check how to call static methdo from static method
- [ ] check matrix ( do not use new Scanner(string) )
- [ ] check auto cast Integer/int
- [X] create getAllInputs
- [X] create runner test all
- [X] create runner builder
- [X] create Integer Input tests
- [X] create SingleIntegerInput without mapper
- [X] create SingleStringInput without mapper
- [X] create empty new line test
- [X] create list of values test
- [X] create list of list of values test (matrix)
