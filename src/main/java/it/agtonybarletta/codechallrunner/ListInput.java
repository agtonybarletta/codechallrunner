package it.agtonybarletta.codechallrunner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.management.RuntimeMBeanException;

import org.apache.commons.text.StringEscapeUtils;

import com.google.common.flogger.FluentLogger;

public class ListInput<T> extends Input<List<T>> {

  // read the next pattern but keep the delimiter
  // https://stackoverflow.com/questions/2206378/how-to-split-a-string-but-also-keep-the-delimiters

  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  private String separator;
  private String prefix;
  private String postfix;
  private InputI<T> input;

  public ListInput(InputI<T> input, String separator, String prefix, String postfix) {
    super(prefix);
    this.input = input;
    this.separator = separator;
    this.prefix = prefix;
    this.postfix = postfix;
  }

  @Override
  public void readData(Scanner scanner) {

    this.data = null;
    this.currentTermnator = null;
    // System.out.println("reading data for ListInput, separator: %s, prefix %s,
    // postfix %s".formatted(this.separator, this.prefix, this.postfix));

    //logger.atInfo().log("readData with terminators: " + this.terminators);

    Pattern oldDelimiter = scanner.delimiter();

    //logger.atInfo().log("old delimiter: " + oldDelimiter.toString());

    List<T> ret = new LinkedList<>();

    try {

      if (this.prefix != null) {
        logger.atInfo().log("entered prefix skipping");
        scanner.skip(this.escapeRegexString(this.prefix));
      }

      //this.input.addTerminator(oldDelimiter.toString());
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
        if (this.terminators.contains(inputTerminator) || 
            inputTerminator == null || 
            inputTerminator.equals(this.postfix) 
            // || this.terminators.contains(StringEscapeUtils.escapeJava(inputTerminator))
        ) {
          
          logger.atInfo().log("enterd stopping condition with: "+ this.terminators + ", " + inputTerminator + ", " + this.postfix);
          if (this.postfix != null && inputTerminator.equals(this.escapeRegexString(this.postfix))) {
            throw new RuntimeException("input ended but postfix " + this.postfix + " expected");
          } else {
            logger.atInfo().log("stopping because terminator equal to postfix" + inputTerminator);
            stop = true;
            this.currentTermnator = inputTerminator;
          }
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
  }

  public String toString() {
    return "{ separator: " + this.separator + ", data: " + this.data.toString() + " }";
  }
}
