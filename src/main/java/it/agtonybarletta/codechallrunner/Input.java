package it.agtonybarletta.codechallrunner;

import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;

public abstract class Input implements InputI{
	public static String EMPTY_LINE = "(?m)\\n";
	public static String NEW_LINE = "\\n";
	public static class Mappers {
		public static final Function<String,Integer> intMapper = (s) -> Integer.valueOf(s);	
		public static final Function<String,String> stringMapper = (s) -> s;	
	}
	protected String terminator;

	public Input(String terminator) {
		this.terminator = terminator;
	}

	public String getTerminator() {
		return terminator;
	}

	public void setTerminator(String terminator) {
		this.terminator = terminator;
	}



}
