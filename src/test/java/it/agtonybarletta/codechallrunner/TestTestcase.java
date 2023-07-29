package it.agtonybarletta.codechallrunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.common.flogger.FluentLogger;

public class TestTestcase {


  private static final FluentLogger logger = FluentLogger.forEnclosingClass();
  /*
  public void test1() {
    try{
      ListInput<Integer> input = 
          new ListInput<Integer>(
            new SingleInteger(),
            ",", 
            "\\(", 
            "\\)"
            ); 
      ListInput<List<Integer>> matrix = new ListInput<List<Integer>>(
          input,
          ",", 
          "\\[", 
          "\\]"
          );

        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputMatrixSquareBrachets")
          .addInput(matrix)
          .build();

        List<?> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 1);
        List<?> list = (List<?>) inputs.get(0);
        assertEquals(list.size(), 3);
        int offset = 0;
        for (int i = 0; i < 3; i++) {
          List<Integer> l = (List<Integer>) list.get(i);  
          assertEquals(l.size(), 3);
          for (int j = 0; j < 3; j++) {
            assertEquals(l.get(j)+offset, j+offset+1);
          }
          offset += 3;
        }
    } catch( Exception e) {
      fail(e);
    }
  }
  */
  public void test1() {
    try{
      ListInput<Integer> input = 
          new ListInput<Integer>(
            new SingleInteger(),
            " ", 
            null, 
            null
            ); 
      ListInput<List<Integer>> matrix = new ListInput<List<Integer>>(
          input,
          "<END>", 
          null, 
          null
          );

        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputMatrix")
          .addInput(matrix)
          .build();

        List<?> inputs = runner.getInput(0);
        assertEquals(inputs.size(), 1);
        logger.atInfo().log("inputs: " + inputs);
        List<?> list = (List<?>) inputs.get(0);
        /*
        assertEquals(list.size(), 3);
        int offset = 0;
        for (int i = 0; i < 3; i++) {
          List<Integer> l = (List<Integer>) list.get(i);  
          assertEquals(l.size(), 3);
          for (int j = 0; j < 3; j++) {
            assertEquals(l.get(j)+offset, j+offset+1);
          }
          offset += 3;
        }
        */
    } catch( Exception e) {
      fail(e);
    }
  }

  /*
  @Test
  @DisplayName("Test list of integers")
  public void testListOfIntegers() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputListIntegers")
          .addInput(new ListInput<Integer>(new SingleInteger(), ",", "", null))
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
*/

}
