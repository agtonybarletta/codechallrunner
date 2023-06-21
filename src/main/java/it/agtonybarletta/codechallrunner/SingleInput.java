package it.agtonybarletta.codechallrunner;

import java.util.Scanner;
import java.util.function.Function;

public class SingleInput<T> extends Input{

  private Function<String,T> mapper;

  
  public SingleInput(Function<String,T> mapper) {
    this.mapper = mapper;
  }

  public SingleInput(String terminator, Function<String,T> mapper) {
    this.terminator = terminator;
    this.mapper = mapper;
  }

  @Override
  public Object readData(Scanner scanner) {
    T data;
    try{
      scanner = scanner.useDelimiter(this.terminator);
      data = this.mapper.apply(scanner.next());
      return data;
    } catch (Exception e) {
      throw new RuntimeException("parsing error: " + e.getMessage());
    }
  }

}
