package it.agtonybarletta.codechallrunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.LinkedList;
import java.util.Scanner;

import it.agtonybarletta.codechallrunner.inputdefinition.InputDefinitionI;
import it.agtonybarletta.codechallrunner.inputdefinition.SingleInputDefinition;
import it.agtonybarletta.codechallrunner.inputdefinition.SingleQuotedStringDefinition;
import it.agtonybarletta.codechallrunner.inputdefinition.SingleStringDefinition;
import it.agtonybarletta.codechallrunner.reader.InputReader;
import it.agtonybarletta.codechallrunner.reader.ReaderOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestString {

  @Test
  @DisplayName("Test single string")
  public void testSingleString() {
    try {
      String input = "string-without-spaces";
      SingleInputDefinition<String> inputDefinition = new SingleStringDefinition();

      ReaderOutput<String> output = new InputReader().read(
              new Scanner(input),
              new LinkedList<>(),
              inputDefinition
      );
      String s = output.getData();
      assertEquals(s, input);

      //TODO move test in its own method

      String inputWithSpaces = "string with spaces";
      output = new InputReader().read(
              new Scanner(inputWithSpaces),
              new LinkedList<>(),
              inputDefinition
      );
      String stringWithSpaces = output.getData();
      assertEquals(stringWithSpaces, inputWithSpaces);

    } catch (Exception e) {
      fail(e);
    }
  }

  @Test
  @DisplayName("Test single quoted string")
  public void testSingleQuotedString() {
    try {
      InputDefinitionI<String> inputDefinition = new SingleQuotedStringDefinition();

      String input = "\"quoted string\"";
      ReaderOutput<String> output = new InputReader().read(
              new Scanner(input),
              new LinkedList<>(),
              inputDefinition
      );

      String unquotedString = output.getData();
      assertEquals(unquotedString, input.substring(1,input.length()-1));
      //TODO move test in its own method

      String inputWithQuotes = "\"quoted string containing \"quotes\".\"";
      output = new InputReader().read(
              new Scanner(inputWithQuotes),
              new LinkedList<>(),
              inputDefinition
      );
      String unquotedStringContainingQuotes = output.getData();
      assertEquals(unquotedStringContainingQuotes, inputWithQuotes.substring(1,inputWithQuotes.length()-1));

    } catch (Exception e) {
      fail(e);
    }
  }


  //TODO test quoted string that has no char

}
