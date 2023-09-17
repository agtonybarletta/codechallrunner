package it.agtonybarletta.codechallrunner;

import it.agtonybarletta.codechallrunner.inputdefinition.SingleInputDefinition;
import it.agtonybarletta.codechallrunner.inputdefinition.SingleIntegerDefinition;
import it.agtonybarletta.codechallrunner.inputdefinition.SingleStringDefinition;
import it.agtonybarletta.codechallrunner.reader.InputReader;
import it.agtonybarletta.codechallrunner.reader.ReaderOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestInteger {
    @Test
    @DisplayName("Test integer")
    public void testSingleString() {
        try {
            String input = "17";
            SingleInputDefinition<Integer> inputDefinition = new SingleIntegerDefinition();

            ReaderOutput<Integer> output = new InputReader().read(
                    new Scanner(input),
                    new LinkedList<>(),
                    inputDefinition
            );
            Integer i = output.getData();
            assertEquals(i.toString(), input);

            //TODO move test in its own method
            String negativeInput = "-127";
            output = new InputReader().read(
                    new Scanner(negativeInput),
                    new LinkedList<>(),
                    inputDefinition
            );
            Integer negativeInteger = output.getData();
            assertEquals(negativeInteger.toString(), negativeInput);

        } catch (Exception e) {
            fail(e);
        }
    }

    //TODO
    /*
    @Test
  @DisplayName("Test single integer parsing error")
  public void testSingleIntegerParsingError() {
    try{
        CodeChallRunner runner = new CodeChallRunnerBuilder()
          .addFile("inputSingleString1")
          .addInput(new SingleInputDefinition<Integer>(Input.Mappers.intMapper))
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
     */
}
