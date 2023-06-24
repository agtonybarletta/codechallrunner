package it.agtonybarletta.codechallrunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import com.google.common.flogger.FluentLogger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParserTest {

  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  @Test
  @DisplayName("Test single string")
  public void testSingleString() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputSingleString1")
          .addInput(new SingleInput<String>(Input.Mappers.stringMapper))
          .build();

        List<?> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 1);
        String s = (String) inputs.get(0);
        assertEquals(s, "string-without-spaces");
    } catch( Exception e) {
      fail(e);
    }
  }
  
  @Test
  @DisplayName("Test single string comma separated")
  public void testSingleStringCommaSeparated() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputSingleStringComma")
          .addInput(new SingleInput<String>(",", Input.Mappers.stringMapper))
          .addInput(new SingleInput<String>(",", Input.Mappers.stringMapper))
          .addInput(new SingleInput<String>(",", Input.Mappers.stringMapper))
          .build();

        List<?> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 3);
        String s = (String) inputs.get(0);
        assertEquals(s, "string with");
        s = (String) inputs.get(1);
        assertEquals(s, "comma separated");
        s = (String) inputs.get(2);
        assertEquals(s, "values");
    } catch( Exception e) {
      fail(e);
    }
  }

  @Test
  @DisplayName("Test multiple strings")
  public void testMultipleStrings() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputMultipleStrings1")
          .addInput(new SingleInput<String>(Input.Mappers.stringMapper))
          .addInput(new SingleInput<String>(Input.Mappers.stringMapper))
          .build();

        List<?> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 2);
        String s = (String) inputs.get(0);
        assertEquals(s, "string-without-spaces");
        String s2 = (String) inputs.get(1);
        assertEquals(s2, "string with spaces");
    } catch( Exception e) {
      fail(e);
    }
  }

  @Test
  @DisplayName("Test multiple testCases")
  public void testMultipleTestCases() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputSingleString1")
          .addInput(new SingleInput<String>(Input.Mappers.stringMapper))
          .build();

        List<?> inputsTestCase1 = runner.getInput(0);
        assertEquals(inputsTestCase1.size(), 1);
        String s = (String) inputsTestCase1.get(0);
        assertEquals(s, "string-without-spaces");
        
        List<?> inputsTestCase2 = runner.getInput(1);
        assertEquals(inputsTestCase2.size(), 1);
        String s2 = (String) inputsTestCase2.get(0);
        assertEquals(s2, "string from another file");

        try{
          List<?> inputs3 = runner.getInput(2);
          fail("Exception FileNotFoundException not thrown");
        } catch (FileNotFoundException e) {

        }
    } catch( Exception e) {
      fail(e);
    }
  }


  @Test
  @DisplayName("Test multiple files")
  public void testMultipleFiles() {
    try{
      CodeChallRunner runner = new CodeChallRunnerBuilder()
        .addFile("inputSingleString1")
        .addInput(new SingleInput<String>(Input.Mappers.stringMapper))
        .addFile("inputMultipleStrings1")
        .addInput(new SingleInput<String>(Input.Mappers.stringMapper))
        .addInput(new SingleInput<String>(Input.Mappers.stringMapper))
        .build();

        List<?> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 3);

        String s = (String) inputs.get(0);
        assertEquals(s, "string-without-spaces");

        String s1 = (String) inputs.get(1);
        assertEquals(s1, "string-without-spaces");

        String s2 = (String) inputs.get(2);
        assertEquals(s2, "string with spaces");

    } catch( Exception e) {
      fail(e);
    }
  }

  @Test
  @DisplayName("Test terminator space")
  public void testTerminatorSpace() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputSingleString1")
          .addInput(new SingleInput<String>(" ", Input.Mappers.stringMapper))
          .addInput(new SingleInput<String>(" ", Input.Mappers.stringMapper))
          .addInput(new SingleInput<String>(" ", Input.Mappers.stringMapper))
          .addInput(new SingleInput<String>(" ", Input.Mappers.stringMapper))
          .build();

        List<?> inputs = runner.getInput(1);
        assertEquals(inputs.size(), 4);
        String s = (String) inputs.get(0);
        assertEquals(s, "string");
        s = (String) inputs.get(1);
        assertEquals(s, "from");
        s = (String) inputs.get(2);
        assertEquals(s, "another");
        s = (String) inputs.get(3);
        assertEquals(s, "file");
    } catch( Exception e) {
      fail("Test single string failed with exception "+ e.getMessage());
    }
  }


  /*
   * INTEGER
   */
  @Test
  @DisplayName("Test single integer")
  public void testSingleInteger() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputSingleInteger")
          .addInput(new SingleInput<Integer>(Input.Mappers.intMapper))
          .build();

        List<?> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 1);
        Integer i = (Integer) inputs.get(0);
        assertEquals(i, -217);
    } catch( Exception e) {
      fail("Test single integer failed with exception "+ e.getMessage());
    }
  }

  @Test
  @DisplayName("Test single integer parsing error")
  public void testSingleIntegerParsingError() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputSingleString1")
          .addInput(new SingleInput<Integer>(Input.Mappers.intMapper))
          .build();

        try{
          List<?> inputs = runner.getInput(0);
          fail("Exception NumberFormatException not thrown");
        } catch (NumberFormatException e) {

        }
    } catch( Exception e) {
      fail("Test single integer failed with exception "+ e.getMessage());
    }
  }


  @Test
  @DisplayName("Test specific single inputs")
  public void testSpecificSingleInputs() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputSingleString1")
          .addInput(new SingleString())
          .addFile("inputSingleInteger")
          .addInput(new SingleInteger())
          .build();

        List<?> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 2);
        assertEquals((String)inputs.get(0), "string-without-spaces");
        Integer i = (Integer) inputs.get(1);
        assertEquals(i, -217);
    } catch( Exception e) {
      fail("Test specific single input failed with exception: "+ e.getMessage());
    }
  }
  @Test
  @DisplayName("Test list of strings")
  public void testListOfStrings() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputListStrings")
          .addInput(new ListInput<String>(new SingleString(), Input.NEW_LINE, "", ""))
          .build();

        List<?> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 1);
        List<String> list = (List<String>) inputs.get(0);
        assertEquals(list.get(0), "string 1");
        assertEquals(list.get(1), "string 2");
        assertEquals(list.get(2), "string 3");
        assertEquals(list.get(3), "string 4");
    } catch( Exception e) {
      fail(e);
    }
  }

  @Test
  @DisplayName("Test list of integers")
  public void testListOfIntegers() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputListIntegers")
          .addInput(new ListInput<Integer>(new SingleInteger(), ",", "", ""))
          .build();

        List<?> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 1);
        List<?> list = (List<?>) inputs.get(0);
        assertEquals(list.size(), 6);
        assertEquals(list.get(0), 1);
        assertEquals(list.get(1), 2);
        assertEquals(list.get(2), 3);
        assertEquals(list.get(3), 4);
        assertEquals(list.get(4), 5);
        assertEquals(list.get(5), -217);
    } catch( Exception e) {
      fail(e);
    }
  }

  @Test
  @DisplayName("Test list with prefix and postfix")
  public void testListPrefixAndPostfix() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputListIntegersWithSquareBrakets")
          .addInput(new ListInput<Integer>(new SingleInteger(), ",", "\\[", "\\]"))
          .build();

        List<?> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 1);
        List<?> list = (List<?>) inputs.get(0);
        assertEquals(list.size(), 6);
        assertEquals(list.get(0), 6);
        assertEquals(list.get(1), 7);
        assertEquals(list.get(2), 8);
        assertEquals(list.get(3), 9);
        assertEquals(list.get(4), 10);
        assertEquals(list.get(5), -217);
    } catch( Exception e) {
      fail(e);
    }
  }

  @Test
  @DisplayName("Test target Integer")
  public void testTargetInteger() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputListIntegersWithSquareBrakets")
          .addInput(new ListInput<Integer>(new SingleInteger(), ",", "\\[", "\\]"))
          .addTargetFile("targetSumList")
          .addTarget(new SingleInteger())
          .build();

        List<?> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 1);
        List<?> list = (List<?>) inputs.get(0);
        assertEquals(list.size(), 6);
        assertEquals(list.get(0), 6);
        assertEquals(list.get(1), 7);
        assertEquals(list.get(2), 8);
        assertEquals(list.get(3), 9);
        assertEquals(list.get(4), 10);
        assertEquals(list.get(5), -217);

        Integer target = (Integer) runner.getTarget(0);
        assertEquals(target, -177);
    } catch( Exception e) {
      fail(e);
    }
  }


}
