package it.agtonybarletta.codechallrunner;

import java.util.Scanner;

public interface InputI {
	public Object readData(Scanner scanner);
	public void setTerminator(String terminator);
	public String getTerminator();

}
