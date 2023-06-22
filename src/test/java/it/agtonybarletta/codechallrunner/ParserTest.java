package it.agtonybarletta.codechallrunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParserTest {

  @Test
  @DisplayName("Test single string")
  public void testSingleString() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputSingleString1")
          .addInput(new SingleInput<String>(Input.Mappers.stringMapper))
          .build();

        List<List<Object>> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 1);
        List<Object> input = inputs.get(0);
        assertEquals(input.size(), 1);
        String s = (String) input.get(0);
        assertEquals(s, "string-without-spaces");
    } catch( Exception e) {
      fail("Test single string failed with exception "+ e.getMessage());
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

        List<List<Object>> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 1);
        List<Object> input = inputs.get(0);
        assertEquals(input.size(), 3);
        String s = (String) input.get(0);
        assertEquals(s, "string with");
        s = (String) input.get(1);
        assertEquals(s, "comma separated");
        s = (String) input.get(2);
        assertEquals(s, "values");
    } catch( Exception e) {
      fail("Test single string comma separated failed with exception "+ e.getMessage());
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

        List<List<Object>> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 1);
        List<Object> input = inputs.get(0);
        assertEquals(input.size(), 2);
        String s = (String) input.get(0);
        assertEquals(s, "string-without-spaces");
        String s2 = (String) input.get(1);
        assertEquals(s2, "string with spaces");
    } catch( Exception e) {
      fail("Test single string failed with exception "+ e.getMessage());
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

        List<List<Object>> inputsTestCase1 = runner.getInput(0);
        assertEquals(inputsTestCase1.size(), 1);
        List<Object> input = inputsTestCase1.get(0);
        assertEquals(input.size(), 1);
        String s = (String) input.get(0);
        assertEquals(s, "string-without-spaces");
        
        List<List<Object>> inputsTestCase2 = runner.getInput(1);
        assertEquals(inputsTestCase2 .size(), 1);
        List<Object> inputTestCase2 = inputsTestCase2.get(0);
        assertEquals(input.size(), 1);
        String s2 = (String) inputTestCase2.get(0);
        assertEquals(s2, "string from another file");

        try{
          List<List<Object>> inputs3 = runner.getInput(2);
          fail("Exception FileNotFoundException not thrown");
        } catch (FileNotFoundException e) {

        }
    } catch( Exception e) {
      fail("Test multiple testcases failed with exception "+ e.getMessage());
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

        List<List<Object>> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 2);

        List<Object> input1 = inputs.get(0);
        assertEquals(input1.size(), 1);

        String s = (String) input1.get(0);
        assertEquals(s, "string-without-spaces");

        List<Object> input2 = inputs.get(1);
        assertEquals(input2.size(), 2);

        String s1 = (String) input2.get(0);
        assertEquals(s1, "string-without-spaces");

        String s2 = (String) input2.get(1);
        assertEquals(s2, "string with spaces");

    } catch( Exception e) {
      fail("Test single string failed with exception "+ e.getMessage());
    }
  }


  @Test
  @DisplayName("Test terminator space")
  public void testTerminatorSpace() {
    // TODOOO
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputMultipleStrings1")
          .addInput(new SingleInput<String>(Input.Mappers.stringMapper))
          .addInput(new SingleInput<String>(Input.Mappers.stringMapper))
          .build();

        List<List<Object>> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 1);
        List<Object> input = inputs.get(0);
        assertEquals(input.size(), 2);
        String s = (String) input.get(0);
        assertEquals(s, "string-without-spaces");
        String s2 = (String) input.get(1);
        assertEquals(s2, "string with spaces");
    } catch( Exception e) {
      fail("Test single string failed with exception "+ e.getMessage());
    }
  }

  /*
  @Test
  @DisplayName("Test parsing error")
  public void testParsingError() {
    try{
      CodeChallRunner runner = new CodeChallRunner();
      runner.addFile("inputSingleStrings2")
        .addInput(new SingleInput<String>(Input.Mappers.stringMapper))
        .parse();
      fail("Parser should have returned error because no input was provided");
    } catch( Exception e) {
      
    }
  }
  */


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

        List<List<Object>> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 1);
        List<Object> input = inputs.get(0);
        assertEquals(input.size(), 1);
        Integer i = (Integer) input.get(0);
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
          List<List<Object>> inputs = runner.getInput(0);
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

        List<List<Object>> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 2);
        List<Object> input = inputs.get(0);
        assertEquals(input.size(), 1);
        assertEquals((String)input.get(0), "string-without-spaces");
        input = inputs.get(1);
        Integer i = (Integer) input.get(0);
        assertEquals(i, -217);
    } catch( Exception e) {
      fail("Test specific single input failed with exception: "+ e.getMessage());
    }
  }
}
