package it.agtonybarletta.codechallrunner;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;

import org.apache.commons.text.StringEscapeUtils;

import com.google.common.flogger.FluentLogger;

public class SingleInput<T> extends Input<T>{

  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  private Function<String,T> mapper;

  
  public SingleInput(Function<String,T> mapper) {
    this.mapper = mapper;
  }

  public SingleInput(String terminator, Function<String,T> mapper) {
    super(terminator);
    this.mapper = mapper;
  }

  @Override
  public void readData(Scanner scanner) {
    
    this.data = null;
    this.currentTermnator = null;
    Pattern oldDelimiter = scanner.delimiter();
    try {

      
      String delimiter = this.getScannerDelimiter();
      logger.atConfig().log("reading data with delimiter: " + delimiter);
      scanner.useDelimiter(delimiter);

      String rawString = scanner.next();
      String rawTerminator = null;

      if (this.terminators.contains(rawString)) {
          return;
      }

      // terminator may not exists, end of the input
      if (scanner.hasNext()) {
        rawTerminator = scanner.next();
      }


      logger.atFinest().log("rawString: " + StringEscapeUtils.escapeJava(rawString) + ", rawTerminator: " + StringEscapeUtils.escapeJava(rawTerminator) );

      rawString = rawString.trim();

      this.data = this.mapper.apply(rawString);

      this.currentTermnator = rawTerminator;

    } catch (NumberFormatException e) {
      throw e;
    } catch (RuntimeException e) {
      throw new RuntimeException("parsing error: " + e.getMessage());
    } finally {
      scanner.useDelimiter(oldDelimiter);
    }
  }

}
