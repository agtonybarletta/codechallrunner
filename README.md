# CodeChallRunner

## About

_CodeChallRunner_ is a Java library that provides a local environment to run and test coding chanllenges.

It emulates what coding challenges websites do ( e.g. leetcode, harkerrank, codeforces)

After you define your input sources, input, and targets, you can run your own method/funciton against the input and validate its accuracy.

It is flexible enought to accept most coding chellenges inputs.

Features:

- data source definition ( files, strings )
- definition of single or colections of various inputs ( Integer, Double, String, List<?>, List<List<?>> etc)
- automatic code runner

## How to use it

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
        ...
        <dependency>
          <groupId>it.agtonybarletta.codechallrunner</groupId>
          <artifactId>codechallrunner</artifactId>
          <version>X.Y.Z</version>
        </dependency>
    </dependencies>
```

Create one or more input files for each testcase. Must be named  `<INPUT_FILENAME>.<TESTCASE_NUMBER>.txt` where `<TESTCASE_NUMER>` is an integer 0..N where N is the number of test cases

```txt
inputListSum.0.txt
------------------
1 2 3 4 5 6
```

Create one target file for each testcase. Must be named  `<TARGET_FILENAME>.<TESTCASE_NUMBER>.txt`

```txt
targetListSum.0.txt
------------------
21
```

Build your own runner and test your function

```java
// ... imports
public class Solution {

  public Integer sumAll(List<Integer> list) {
    int acc = 0;
    for (Integer i : list) {
      acc += i;
    }
    return acc;
  }

  public static void main(String[] args) {
    CodeChallRunner runner = new CodeChallRunnerBuilder()
        .addFile("inputListSum")
        .addInput(new ListInput<Integer>(new SingleInteger(), " ", null, null))
        .addTargetFile("targetListSum")
        .addTarget(new SingleInteger())
        .build();

    Boolean b = runner.test(new Solution(), "sumAll");
    System.out.println("Tests run with result: " + b);
  }
}
```

This runner will go through all your inputs files and test the function `sumAll` against them.

## Examples of Input definitions

## Known issues

1. Files must be in the classpath
2. Autoboxing and primitive types do not work yet.

## License

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version. 
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details. 
You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

