package it.agtonybarletta.codechallrunner.inputdefinition;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class InputDefinition<T> implements InputDefinitionI<T> {


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

  public InputDefinition() {
    this(InputDefinition.NEW_LINE);
  }

  public InputDefinition(String terminator) {
    this.terminators = new LinkedList<>();
    this.terminators.add(terminator);
  }

  @Override
  public List<String> getTerminators() {
      return this.terminators;
  }


  //@Override
  public void addTerminator(String terminator) {
    this.terminators.add(terminator);
  }

  //@Override
  public T getData() {
    return this.data;  
  }

  @Override
  public String getCurrentTerminator() {
    return this.currentTermnator;
  }



}
