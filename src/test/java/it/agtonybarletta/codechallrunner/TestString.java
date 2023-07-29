package it.agtonybarletta.codechallrunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestString {

  @Test
  @DisplayName("Test single string")
  public void testSingleString() {
    try {
      String input = "string-without-space";
      Input<String> inputDefinition = new SingleString();

      inputDefinition.readData(new Scanner(input));
      String s = inputDefinition.getData();
      assertEquals(s, "string-without-space");

      String target = "string with spaces";
      inputDefinition.readData(new Scanner(target));
      String stringWithSpaces = inputDefinition.getData();
      assertEquals(stringWithSpaces, target);

    } catch (Exception e) {
      fail(e);
    }
  }

  @Test
  @DisplayName("Test single quoted string")
  public void testSingleQuotedString() {
    try {
      Input<String> inputDefinition = new SingleQuotedString();

      String target = "\"quoted string\"";
      inputDefinition.readData(new Scanner(target));
      String unquotedString = inputDefinition.getData();
      assertEquals(unquotedString, "quoted string");


      String targetWithQuotes = "\"quoted string containing \"quotes\".\"";
      inputDefinition.readData(new Scanner(targetWithQuotes));
      String unquotedStringContainingQuotes = inputDefinition.getData();
      assertEquals(unquotedStringContainingQuotes, "quoted string containing \"quotes\".");

    } catch (Exception e) {
      fail(e);
    }
  }
}
