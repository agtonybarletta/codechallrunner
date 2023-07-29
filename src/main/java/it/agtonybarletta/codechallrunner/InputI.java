package it.agtonybarletta.codechallrunner;

import java.util.List;
import java.util.Scanner;

public interface InputI<T> {
	public void readData(Scanner scanner);
	public List<String> getTerminators();
    public void addTerminator(String terminator);
    public T getData();
    public String getCurrentTerminator();
}
