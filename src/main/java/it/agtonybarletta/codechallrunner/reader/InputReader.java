package it.agtonybarletta.codechallrunner.reader;

import com.google.common.flogger.FluentLogger;
import it.agtonybarletta.codechallrunner.inputdefinition.*;
import org.apache.commons.text.StringEscapeUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputReader {


    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    public <T> ReaderOutput<T> read(Scanner scanner, List<String> parentTerminators, InputDefinitionI<T> input) {
        Pattern oldDelimiter = scanner.delimiter();
        ReaderOutput<T> ret = new ReaderOutput<>();
        try {
            List<String> terminators = new LinkedList<>(parentTerminators);
            terminators.addAll(input.getTerminators());
            String delimiter = InputDefinitionUtils.getScannerDelimiter(terminators);
            logger.atConfig().log("reading data with delimiter: " + delimiter);
            scanner.useDelimiter(delimiter);

            String rawString = scanner.next();
            String rawTerminator = null;

            // case no data, terminators only
            if (terminators.contains(rawString)) {
                ret.terminator = rawString;

            } else {

                if (scanner.hasNext()) {
                    rawTerminator = scanner.next();
                }

                logger.atFinest().log("rawString: " + StringEscapeUtils.escapeJava(rawString) + ", rawTerminator: " + StringEscapeUtils.escapeJava(rawTerminator) );

                rawString = rawString.trim();

                if( input instanceof SingleInputDefinition ) {
                    ret.data = ((SingleInputDefinition<T>)input).getMapper().apply(rawString);

                    ret.terminator = rawTerminator;
                } else {
                    // implement while loop logic
                    ListInputDefinition<?> list = (ListInputDefinition<?>)input ;
                    ReaderOutput<?> tmp = read(scanner, terminators, list.getInputDefinition());
                }

            }

        } catch (NumberFormatException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new RuntimeException("parsing error: " + e.getMessage());
        } finally {
            scanner.useDelimiter(oldDelimiter);
        }
        return ret;
    }
}
