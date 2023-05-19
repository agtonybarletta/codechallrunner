package it.agtonybarletta.codechallrunner;

import java.util.Scanner;
import java.util.regex.Pattern;

public class StringInput extends Input{

	private String data;

	public StringInput(String terminator) {
		super(terminator);
	}

	public void readData(Scanner scanner){
		Pattern oldDelimiter = scanner.delimiter();
		scanner = scanner.useDelimiter(this.terminator);
		this.data = scanner.next();
		//System.out.println("data: " + this.data);
		if(scanner.hasNext())scanner.skip(this.terminator);
		scanner.useDelimiter(oldDelimiter);
	}
	public Object getData(){
		return this.data;
	}

	public String toString(){
		return "{ terminator: "+ this.terminator + ", data: "+ this.data+ " }";
	}
}
