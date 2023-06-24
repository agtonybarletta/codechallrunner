package it.agtonybarletta.codechallrunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RunnerTest{

  @Test
  @DisplayName("Test single run")
  public void testSingleRunFail() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputListIntegersWithSquareBrakets")
          .addInput(new ListInput<Integer>(new SingleInteger(), ",", "\\[", "\\]"))
          .addTargetFile("inputSingleInteger")
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
        assertEquals(target, -217);

        assertFalse(runner.testSingleTestCase(0, this, "sumAll").booleanValue());
    } catch( Exception e) {
      fail(e);
    }
  }

  @Test
  @DisplayName("Test all testcases")
  public void testAllTestCase() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputListSum")
          .addInput(new ListInput<Integer>(new SingleInteger(), " ", "", ""))
          .addTargetFile("targetListSum")
          .addTarget(new SingleInteger())
          .build();


        Boolean b = runner.test(this, "sumAll");
        assertTrue(b);
    } catch( Exception e) {
      fail(e);
    }
  }

  @Test
  @DisplayName("Test all testcases false")
  public void testAllTestCaseFalse() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputListSum")
          .addInput(new ListInput<Integer>(new SingleInteger(), " ", "", Input.NEW_LINE))
          .addTargetFile("targetListSum")
          .addTarget(new SingleInteger())
          .build();


        Boolean b = runner.test(this, "multiplyAll");
        assertFalse(b);
    } catch( Exception e) {
      fail(e);
    }
  }

  @Test
  @DisplayName("Test all testcases no file found")
  public void testAllTestCaseNoFileFound() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("nonExistingFile")
          .addInput(new ListInput<Integer>(new SingleInteger(), " ", "", Input.NEW_LINE))
          .addTargetFile("targetListSum")
          .addTarget(new SingleInteger())
          .build();

        Boolean b = runner.test(this, "multiplyAll");
        fail("Exception File Not found not thrown");
    } catch( Exception e) {
    }
  }

  @Test
  @DisplayName("Test all testcases no target file found")
  public void testAllTestCaseNoTargetFileFound() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputListSum")
          .addInput(new ListInput<Integer>(new SingleInteger(), " ", "", Input.NEW_LINE))
          .addTargetFile("nonExistingTargetFile")
          .addTarget(new SingleInteger())
          .build();

        Boolean b = runner.test(this, "multiplyAll");
        fail("Exception File Not found not thrown");
    } catch( Exception e) {
    }
  }

  public Integer sumAll(List<Integer> nums) {
    return nums.stream().mapToInt(Integer::valueOf).sum();
  }

  public Integer multiplyAll(List<Integer> nums) {
    int ret = 1;
    for (int i : nums) {
      ret *= i;
    }
    return ret;
  }


  /*
  @Test
  @DisplayName("Test method")
  public void testMethod() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputListIntegersWithSquareBrakets")
          .addInput(new ListInput<Integer>(new SingleInteger(), ",", "\\[", "\\]"))
          .addTargetFile("inputSingleInteger")
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
        assertEquals(target, -217);

        assertFalse(runner.testSingleTestCaseMethod(0, this, "sumAll").booleanValue());
    } catch( Exception e) {
      fail(e);
    }
  }
  */

}
