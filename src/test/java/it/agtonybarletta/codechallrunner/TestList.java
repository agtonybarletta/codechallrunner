package it.agtonybarletta.codechallrunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.common.flogger.FluentLogger;

public class TestList {

  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  @Test
  @DisplayName("Test list of list")
  public void testListOfList() {

    Logger.getLogger("it.agtonybarletta.codechallrunner").setLevel(Level.FINER);
    //String input = "1 2 3<END> 4 5 6<END> 7 8 9<END>";
    String input = "1 2 3<END>4 5 6<END>7 8 9<END>";
    ListInput<Integer> listOfIntegerInputDefinition = new ListInput<Integer>(
        new SingleInteger(),
        " ",
        null,
        null);
    ListInput<List<Integer>> matrix = new ListInput<List<Integer>>(
        listOfIntegerInputDefinition,
        "<END>",
        null,
        null);

    try {

      Scanner scanner = new Scanner(input);
      matrix.readData(scanner);
      List<List<Integer>> list = matrix.getData();
      assertEquals(list.size(), 3);

    } catch (Exception e) {
      fail(e);
    }
  }


  @Test
  @DisplayName("Test list of list with duplicated terminators")
  public void testListOfListWithDuplicatedTerminators() {

    String input = "1, 2, 3 <END> 4, 5, 6 <END> 7 ,8, 9<END>";
    //String input = "1 2 3<END>4 5 6<END>7 8 9<END>";
    ListInput<Integer> listOfIntegerInputDefinition = new ListInput<Integer>(
        new SingleInteger(),
        ",",
        null,
        null);
    ListInput<List<Integer>> matrix = new ListInput<List<Integer>>(
        listOfIntegerInputDefinition,
        "<END>",
        null,
        null);

    try {

      Scanner scanner = new Scanner(input);
      matrix.readData(scanner);
      List<List<Integer>> list = matrix.getData();
      assertEquals(list.size(), 3);

    } catch (Exception e) {
      fail(e);
    }
  }

  @Test
  @DisplayName("Test matrix multiple lines")
  public void testMatrixMultipleLines() {

    Logger.getLogger("it.agtonybarletta.codechallrunner").setLevel(Level.INFO);
    String input = "1 2 3\n" + 
                   "4 5 6\n" +
                   "7 8 9";
    //String input = "1 2 3<END>4 5 6<END>7 8 9<END>";
    ListInput<Integer> listOfIntegerInputDefinition = new ListInput<Integer>(
        new SingleInteger(),
        " ",
        null,
        null);
    ListInput<List<Integer>> matrix = new ListInput<List<Integer>>(
        listOfIntegerInputDefinition,
        Input.NEW_LINE,
        null,
        null);

    try {

      Scanner scanner = new Scanner(input);
      matrix.readData(scanner);
      List<List<Integer>> list = matrix.getData();
      logger.atInfo().log("list: " + list);
      assertEquals(list.size(), 3);

    } catch (Exception e) {
      fail(e);
    }
  }
}
