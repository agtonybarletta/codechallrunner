package it.agtonybarletta.codechallrunner;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.text.StringEscapeUtils;

public abstract class Input<T> implements InputI<T>{

  public static String EMPTY_LINE = "(?m)\\n";
  public static String NEW_LINE = "\n";
  public static String SPACE = " ";

  public static class Mappers {
    public static final Function<String,Integer> intMapper = (s) -> Integer.parseInt(s);	
    public static final Function<String,String> stringMapper = (s) -> s;	
    public static final Function<String,String> quotedStringMapper = (s) -> s.substring(1, s.length()-1);	
  }

  protected List<String> terminators;
  protected T data;
  protected String currentTermnator;

  public Input() {
    this(Input.NEW_LINE);
  }

  public Input(String terminator) {
    this.terminators = new LinkedList<>();
    this.terminators.add(terminator);
  }

  @Override
  public List<String> getTerminators() {
      return this.terminators;
  }


  @Override
  public void addTerminator(String terminator) {
    this.terminators.add(terminator);
  }

  @Override
  public T getData() {
    return this.data;  
  }

  /*
  @Override
  protected void setTerminators(List<String> terminators) {
      this.terminators = terminators;
  }
  */

  @Override
  public String getCurrentTerminator() {
    return this.currentTermnator;
  }

  protected String getScannerDelimiter() {
    return this.terminators.stream()
    .map( s -> escapeRegexString(s))
    .map( s -> "(?<=" + s + ")|(?=" + s + ")" ).collect(Collectors.joining("|"));
  }
  protected String escapeRegexString(String s) {
    if (s == null) return null;
    String toBeEscape = "\\.[]{}()*+-=!?^$|";
    for( char c : toBeEscape.toCharArray() ) {
      s = s.replace(Character.toString(c), "\\" + c);
    }
    System.out.println(s);
    return s;
  }

}
