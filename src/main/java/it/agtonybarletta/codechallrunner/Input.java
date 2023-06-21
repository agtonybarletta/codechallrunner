package it.agtonybarletta.codechallrunner;

import java.util.function.Function;

public abstract class Input implements InputI{

  public static String EMPTY_LINE = "(?m)\\n";
  public static String NEW_LINE = "\\n";
  public static String SPACE = "\\s";

  public static class Mappers {
    public static final Function<String,Integer> intMapper = (s) -> Integer.valueOf(s);	
    public static final Function<String,String> stringMapper = (s) -> s;	
  }

  protected String terminator;

  public Input() {
    this.terminator = Input.NEW_LINE;
  }

  public String getTerminator() {
      return terminator;
  }

  public void setTerminator(String terminator) {
      this.terminator = terminator;
  }
}
