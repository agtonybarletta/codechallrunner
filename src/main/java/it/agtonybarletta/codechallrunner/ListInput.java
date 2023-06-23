package it.agtonybarletta.codechallrunner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.management.RuntimeMBeanException;

import com.google.common.flogger.FluentLogger;

public class ListInput<T> extends InputDecorator<List<T>, T>{

	private static final FluentLogger logger = FluentLogger.forEnclosingClass();

	private String separator;
    private String prefix;
    private String postfix;

	public ListInput(InputI<T> input, String separator, String prefix, String postfix) {
        super(input);
		this.separator = separator;
        this.prefix = prefix;
        this.postfix = postfix;
	}
	
  public List<T> readData(Scanner scanner){
    //System.out.println("reading data for ListInput, separator: %s, prefix %s, postfix %s".formatted(this.separator, this.prefix, this.postfix));
    Logger.getLogger("it.agtonybarletta.codechallrunner").setLevel(Level.OFF);
    Pattern oldDelimiter = scanner.delimiter();	

    if (!this.prefix.equals("")) {
      logger.atInfo().log("entered prefix skipping");
      scanner.skip(this.prefix);
    } else {
      logger.atInfo().log("not entered prefix skipping");
    }

    String listData;
    if(!this.postfix.equals("")){
      logger.atInfo().log("entered postfix skipping");
      scanner.useDelimiter(this.postfix);
      logger.atInfo().log("has postfix");
      listData = scanner.next();  
    } else {
      logger.atInfo().log("not has postfix");
      scanner.useDelimiter("\\Z");
      listData = scanner.next();
    }

    logger.atInfo().log("string containing the list---\n%s\n---", listData.replace("\n", "\\n"));
    //System.out.println("string containing the list "+ listData );

    Scanner scannerList = new Scanner(listData);
    scannerList.useDelimiter(this.separator);
    List<T> ret = new LinkedList<>();
    while(scannerList.hasNext()){
      this.input.setTerminator(this.separator);
      T data = this.input.readData(scannerList);
      ret.add(data);
      //System.out.println(ret);
    }
    scannerList.close();

    scanner.useDelimiter(oldDelimiter);

    logger.atInfo().log("end readData. data: %s", ret);
    return ret;
  }

	public String toString(){
		//return "{ terminator: "+ this.terminator + ", separator: "+ this.separator + ", data: "+ this.data.toString()+ " }";
        return "";
	}
		
}
