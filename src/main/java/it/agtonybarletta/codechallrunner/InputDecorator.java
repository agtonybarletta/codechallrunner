package it.agtonybarletta.codechallrunner;

import java.util.Scanner;

public abstract class InputDecorator<O, T> implements InputI<O> {

  protected InputI<T> input; 

  public InputDecorator(InputI<T> input) {
    this.input = input;
  }

  @Override
  public void setTerminator(String terminator) {
    this.input.setTerminator(terminator);
  }

  @Override
  public String getTerminator() {
    return this.input.getTerminator();
  }

}
