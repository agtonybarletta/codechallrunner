package it.agtonybarletta.codechallrunner;

import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;

public class SingleInput<T> extends Input{

	private T data;
	Function<String,T> mapper;

	public SingleInput(String terminator, Function<String,T> mapper) {
		super(terminator);
		this.mapper = mapper;
	}

	@Override
	public void readData(Scanner scanner) {
		Pattern oldDelimiter = scanner.delimiter();
		scanner = scanner.useDelimiter(this.terminator);
		this.data =  this.mapper.apply(scanner.next());
	}

	@Override
	public T getData() {
		return this.data;
	}

	public String toString(){
		return "{ terminator: "+ this.terminator + ", data: "+ this.data.toString()+ " }";
	}

}
