package it.agtonybarletta.codechallrunner;

import java.util.Scanner;
import java.util.function.Function;

public class SingleInput<T> extends Input<T>{

  private Function<String,T> mapper;

  
  public SingleInput(Function<String,T> mapper) {
    this.mapper = mapper;
  }

  public SingleInput(String terminator, Function<String,T> mapper) {
    this.terminator = terminator;
    this.mapper = mapper;
  }

  @Override
  public T readData(Scanner scanner) {
    T data;
    try{
      scanner = scanner.useDelimiter(this.terminator);
      String rawString = scanner.next();
      rawString = rawString.trim();
      data = this.mapper.apply(rawString);
      return data;
    } catch (NumberFormatException e) {
      throw e;
    } catch (RuntimeException e) {
      throw new RuntimeException("parsing error: " + e.getMessage());
    }
  }

}
