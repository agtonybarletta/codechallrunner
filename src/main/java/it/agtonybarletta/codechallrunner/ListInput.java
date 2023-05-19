package it.agtonybarletta.codechallrunner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.google.common.flogger.FluentLogger;

public class ListInput<T extends Object> extends Input{

	private static final FluentLogger logger = FluentLogger.forEnclosingClass();


	private String separator;
	private List<T> data;
	private Class<? extends T> clazz;
	public ListInput(String terminator, String separator, Class<? extends T> clazz) {
		super(terminator);
		this.separator = separator;
		this.data = new LinkedList<>();
		this.clazz = clazz;
	}
	
	public void readData(Scanner scanner){

		logger.atInfo().log("reading data for ListInput of type %s, terminator %s, delimiter %s", this.clazz.toString(),this.terminator, this.separator);
		Pattern oldDelimiter = scanner.delimiter();	
		String listData = scanner.useDelimiter(Pattern.MULTILINE + this.terminator).next();
		logger.atInfo().log(" string containing the list %s", listData);
	
		Scanner scannerList = new Scanner(listData);
		scannerList.useDelimiter(this.separator);
		while(scannerList.hasNext()){
			if(clazz.isInstance(new String())){
				String s = scannerList.next();
				logger.atInfo().log("parsing string %s", s);
				this.data.add((T) s);
			}
			if(clazz.isInstance(Integer.class)){
				Integer i = Integer.valueOf( scannerList.next());
				logger.atInfo().log("parsing integer %s", i);
				this.data.add((T) i );
			}
		}
		scannerList.close();

		if(scanner.hasNext())scanner.skip(this.terminator);
		scanner.useDelimiter(oldDelimiter);

		logger.atInfo().log("end readData. data: %s", this.toString());
	}

	@Override
	public List<T> getData() {
		return this.data;
	}

	public String toString(){
		return "{ terminator: "+ this.terminator + ", separator: "+ this.separator + ", data: "+ this.data.toString()+ " }";
	}
		
}
