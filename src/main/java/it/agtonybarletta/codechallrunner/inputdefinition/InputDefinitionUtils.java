package it.agtonybarletta.codechallrunner.inputdefinition;

import java.util.List;
import java.util.stream.Collectors;

public class InputDefinitionUtils {
    public static String getScannerDelimiter(List<String> terminators) {
        return terminators.stream()
                .map( s -> InputDefinitionUtils.escapeRegexString(s))
                .map( s -> "(?<=" + s + ")|(?=" + s + ")" ).collect(Collectors.joining("|"));
    }
    public static String escapeRegexString(String s) {

        if (s == null)
            return null;
        String toBeEscape = "\\.[]{}()*+-=!?^$|";
        for( char c : toBeEscape.toCharArray() ) {
            s = s.replace(Character.toString(c), "\\" + c);
        }
        return s;
    }
}
