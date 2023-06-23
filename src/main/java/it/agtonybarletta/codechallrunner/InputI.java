package it.agtonybarletta.codechallrunner;

import java.util.Scanner;

public interface InputI<T> {
	public T readData(Scanner scanner) throws NumberFormatException;
	public void setTerminator(String terminator);
	public String getTerminator();

}
