package it.agtonybarletta.codechallrunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Scanner;

import it.agtonybarletta.codechallrunner.inputdefinition.SingleIntegerDefinition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ListTest {

  /*
  @Test
  @DisplayName("Test single string")
  public void testListOfList() {

    String input = "1 2 3<END> 4 5 6<END> 7 8 9<END>";
    ListInput<Integer> listOfIntegerInputDefinition = new ListInput<Integer>(
        new SingleIntegerDefinition(),
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

   */
}
