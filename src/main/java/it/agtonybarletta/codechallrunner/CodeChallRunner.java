package it.agtonybarletta.codechallrunner;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class CodeChallRunner {

  private List<String> inputFiles;
  private String targetFile;
  private Map<String,List<InputI<?>>> fileInputMap;

  public CodeChallRunner( List<String> inputFiles, Map<String, List<InputI<?>>> fileInputMap, String targetFile) {
    this.inputFiles = inputFiles;
    this.fileInputMap = fileInputMap;
    this.targetFile = targetFile;
  }

  public List<?> getFileInput(Integer testCaseNumber, List<String> files) throws FileNotFoundException, NumberFormatException {
    List<Object> ret = new LinkedList<>();

    Scanner scanner = null;
    try{
      for(String s : files){

        String fileName = s + "."+ testCaseNumber+".txt";
        InputStream inputFile = this.getClass().getClassLoader().getResourceAsStream(fileName);
        
        if (inputFile == null) {
          throw new FileNotFoundException("File not found: "+fileName);
        }

        scanner = new Scanner(inputFile);
        for (InputI<?> i : fileInputMap.get(s)) {
          Scanner data = scanner.useDelimiter(i.getTerminator());
          ret.add(i.readData(data));
        }
      }
      // TODO improve readibility
    } catch (RuntimeException e) {
      throw e;
    } finally {
      if (scanner != null) scanner.close();
    }
    return ret;
  }


  public List<?> getInput(Integer testCaseNumber) throws FileNotFoundException, NumberFormatException{
     return this.getFileInput(testCaseNumber, this.inputFiles);
  }
  public Object getTarget(Integer testCaseNumber) throws FileNotFoundException, NumberFormatException{
     return this.getFileInput(testCaseNumber, Arrays.asList(this.targetFile)).get(0);
  }

  public Boolean testSingleTestCase(Integer testCaseNumber, Function f) {
     
    try {
		List<?> input = this.getInput(testCaseNumber);
        Object target = this.getTarget(testCaseNumber);
        Object output = f.apply(input);
        return output.equals(target);
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return false;
  }

}
