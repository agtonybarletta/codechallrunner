package it.agtonybarletta.codechallrunner.inputdefinition;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;

import it.agtonybarletta.codechallrunner.reader.ReaderOutput;
import org.apache.commons.text.StringEscapeUtils;

import com.google.common.flogger.FluentLogger;

public class SingleInputDefinition<T> extends InputDefinition<T> {

  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  private Function<String,T> mapper;

  
  public SingleInputDefinition(Function<String,T> mapper) {
    this.mapper = mapper;
  }

  public SingleInputDefinition(String terminator, Function<String,T> mapper) {
    super(terminator);
    this.mapper = mapper;
  }

  @Override
  public List<String> setTerminators(List<String> terminators) {
    return null;
  }

  // todo use interface ?
  public Function<String, T> getMapper() {
    return this.mapper;
  }


  // TODO move to mapper/consumer
  /*
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
  }*/

  /*
    1. Do not use inputDefinition terminators as a scanner terminator
      -> do not add parent terminators to current terminators
      = inputDefinitionTerminators only contains the input terinator,
      In this way we split from definition to computation
    1.2 The stack logic must be implemented as a list of terminators that get added and then removed in a rec function
    2. Create "separate methods" to deal with inputs reading ... in this way inputDefinition contains only
    input definition, no logic about reading input
    3. create a recursive function to read all input ?
    4. separate Mapper from input definitions, create a Mappers class outside InputDefinition
    5. TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST

   */

}
