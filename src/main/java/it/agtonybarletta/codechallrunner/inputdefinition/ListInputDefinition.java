package it.agtonybarletta.codechallrunner.inputdefinition;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import it.agtonybarletta.codechallrunner.inputdefinition.InputDefinition;
import it.agtonybarletta.codechallrunner.inputdefinition.InputDefinitionI;
import org.apache.commons.text.StringEscapeUtils;

import com.google.common.flogger.FluentLogger;

public class ListInputDefinition<T> extends InputDefinition<List<T>> {

  // read the next pattern but keep the delimiter
  // https://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters

  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  private String separator;
  private String prefix;
  private String postfix;
  private InputDefinitionI<T> input;
  private boolean exitedForTerminator; 

  public ListInputDefinition(InputDefinitionI<T> input, String separator, String prefix, String postfix) {
    super(prefix);
    this.input = input;
    this.separator = separator;
    this.prefix = prefix;
    this.postfix = postfix;
  }



  // TODO move to mapper/consumer
  /*@Override
  public void readData(Scanner scanner) {

    this.data = null;
    this.currentTermnator = null;

    logger.atInfo().log("readData with terminators: " + this.terminators);

    Pattern oldDelimiter = scanner.delimiter();

    //logger.atInfo().log("old delimiter: " + oldDelimiter.toString());

    List<T> ret = new LinkedList<>();

    try {

      if (this.prefix != null) {
        logger.atInfo().log("entered prefix skipping");
        logger.atInfo().log("escaping: "+ this.prefix);
        scanner.skip(this.escapeRegexString(this.prefix));
      }

      this.input.addTerminator(this.separator);

      this.terminators.forEach( t -> this.input.addTerminator(t));

      if (this.postfix != null) {
        this.input.addTerminator(this.postfix);
      }

      boolean stop = false;

      while (scanner.hasNext() && !stop) {

        this.input.readData(scanner);

        T inputData = this.input.getData();

        String inputTerminator = this.input.getCurrentTerminator();

        logger.atInfo().log("inputData: " + inputData + ", inputTerminator: " +StringEscapeUtils.escapeJava(inputTerminator));

        if (inputData == null) {
          logger.atInfo().log("read data as null");
          continue;
        }

        // compare inputTerminator with this.terminators too
        if ((this.terminators.contains(inputTerminator) && this.postfix == null) ||
            inputTerminator == null || 
            inputTerminator.equals(this.postfix)
        ) {
          
          logger.atInfo().log("entered stopping condition with: "+ this.terminators + ", " + inputTerminator + ", " + this.postfix);
          if (this.postfix != null && inputTerminator.equals(this.escapeRegexString(this.postfix))) {
            throw new RuntimeException("input ended but postfix " + this.postfix + " expected");
          }
          logger.atInfo().log("stopping because terminator equal to postfix" + inputTerminator);
          stop = true;
          this.currentTermnator = inputTerminator;
        }
        ret.add(inputData);
      }
    } catch (Exception e) {
      throw e;
    } finally {
      scanner.useDelimiter(oldDelimiter);
    }

    logger.atInfo().log("end readData. data: %s", ret);

    this.data = ret;
  }*/

  /*
  public boolean exitCondition(Input<?> input List<String> terminators, String postfix, String inputTerminator, List<String> inputTerminators, String inputPostfix) {
    // case 1 exit beacause inner input got outer separator
    // es a,b,c|e,f,g
    // inner input terminated with |, which is outer separator
    if ( inputTerminator ==  null ) {
      // exit if input didn't set any terminator
      // case end of input
      return true;
    } else if ( inputTerminator )
    return false;
  }*/

  public String toString() {
    return "{ separator: " + this.separator + ", data: " + this.data.toString() + " }";
  }

  @Override
  public List<String> setTerminators(List<String> terminators) {
    return this.terminators = terminators;
  }

  public InputDefinitionI<T> getInputDefinition() {
    return this.input;
  }
}
