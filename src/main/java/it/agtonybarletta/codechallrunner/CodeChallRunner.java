package it.agtonybarletta.codechallrunner;

import com.google.common.flogger.FluentLogger;
import it.agtonybarletta.codechallrunner.inputdefinition.InputDefinitionI;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.XMLFormatter;

public class CodeChallRunner {

  private static final FluentLogger logger = FluentLogger.forEnclosingClass();


  private List<String> inputFiles;
  private String targetFile;
  private Map<String,List<InputDefinitionI<?>>> fileInputMap;

  public CodeChallRunner( List<String> inputFiles, Map<String, List<InputDefinitionI<?>>> fileInputMap, String targetFile) {
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
        for (InputDefinitionI<?> i : fileInputMap.get(s)) {
          Scanner data = scanner;
          //i.readData(data);
          //ret.add(i.getData());
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

  public Boolean test(Object caller, String method) {
    
    int i = 0;
    boolean res = true;
    try{
      while(true) {
        res &= testSingleTestCase(i, caller, method);
        i++;
      }
    } catch ( FileNotFoundException e) {
      if (i == 0) {
        throw new RuntimeException("No file found");
      }
    }
    return res;

  }

  public Boolean testSingleTestCase(Integer testCaseNumber, Object caller, String methodName) throws NumberFormatException, FileNotFoundException {

    logger.atInfo().log(this.getClass().getName());
    Arrays.stream(LogManager.getLogManager().getLogger(this.getClass().getName()).getHandlers()).forEach(h -> h.setFormatter(new XMLFormatter()));
    try {
      Method[] methods = caller.getClass().getMethods();
      Method m = null;
      for (int i = 0; i < methods.length; i++) {
        if (methods[i].getName().equals(methodName)) {
          if (m == null) {
            m = methods[i];
          } else {
            throw new RuntimeException("more than one method");
          }
        }
      }
      if (m == null) {
        throw new RuntimeException("No method found");
      }
      logger.atInfo().log("Test case #" + testCaseNumber);

      List<?> input = this.getInput(testCaseNumber);
      Object target = this.getTarget(testCaseNumber);
      Class<?>[] types = m.getParameterTypes();

      if (types.length != input.size()) {
        throw new RuntimeException("method input missmatch");
      }

      Object[] parameters = new Object[types.length];
      for (int i = 0; i < types.length; i++) {

        //System.out.println("1: " + input.get(i).getClass());
        //System.out.println("2: " + types[i]);

        if (types[i].isAssignableFrom(input.get(i).getClass())) {
          parameters[i] = input.get(i);
        } else {
          throw new  RuntimeException("cannot cast " + input.get(i).getClass() + " to " + types[i] );
        }
      }
      logger.atInfo().log("Inputs: " + Arrays.toString(parameters));
      logger.atInfo().log("Expected: " + target);

      Object ret = m.invoke(caller,parameters);
      logger.atInfo().log("Got: " + ret);
      boolean result = ret.equals(target);
      if (result) {
        logger.atInfo().log("SUCCESS");
      } else {
        logger.atSevere().log("FAIL");

      }
      return result;
    } catch (IllegalAccessException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }

  }
  /*public Boolean testSingleTestCase(Integer testCaseNumber, Function f) {
     
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
  */

}
