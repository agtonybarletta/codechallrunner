package it.agtonybarletta.codechallrunner;

import java.util.Scanner;

public interface InputI {
	public void readData(Scanner scanner);
	public Object getData();
	public void setTerminator(String terminator);
	public String getTerminator();

}
