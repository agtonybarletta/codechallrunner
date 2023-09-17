package it.agtonybarletta.codechallrunner.inputdefinition;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public interface InputDefinitionI<T> {
	//public void readData(Scanner scanner);
	public List<String> getTerminators();
    public List<String> setTerminators(List<String> terminators);
    //public void addTerminator(String terminator);
    //public T getData();
    public String getCurrentTerminator();

}
