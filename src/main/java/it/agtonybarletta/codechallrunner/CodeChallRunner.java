package it.agtonybarletta.codechallrunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CodeChallRunner {

  private List<String> files;
  private Map<String,List<Input<?>>> fileInputMap;

  public CodeChallRunner( List<String> files, Map<String, List<Input<?>>> fileInputMap) {
    this.files = files;
    this.fileInputMap = fileInputMap;
  }



  public CodeChallRunner parse(){
    // TODO: check if there is at least 1 input
    for(String s : files){
        //File myObj = new File(s + ".0.txt");
        InputStream inputFile = this.getClass().getClassLoader().getResourceAsStream(s + ".0.txt");
        //System.out.println(inputFile.toString());
        Scanner myReader = new Scanner(inputFile);//new Scanner(myObj);
        for ( Input<?> i : fileInputMap.get(s))
            i.readData(myReader);
            
        myReader.close();
    }
    return this;
  }


  /*public boolean test(Function<Object[],Object> runnerFunction){
      //System.out.println("input: " + i.getData());
      Object output = runnerFunction.apply(this.getInput().toArray());
      return this.getOutput().equals(output);
  }

  private void loadTestCaseNumber(Integer testCaseNumber) {
    // TODO thinks about how to manage multiple data ... runtime ?
     
  }
  */

  public List<List<Object>> getInput(Integer testCaseNumber) throws FileNotFoundException, NumberFormatException{
    List<List<Object>> ret = new LinkedList<>();

    Scanner scanner = null;
    try{
      for(String s : files){

        String fileName = s + "."+ testCaseNumber+".txt";
        InputStream inputFile = this.getClass().getClassLoader().getResourceAsStream(fileName);
        
        if (inputFile == null) {
          throw new FileNotFoundException("File not found: "+fileName);
        }

        List<Object> input = new LinkedList<>();
        scanner = new Scanner(inputFile);
        for (Input<?> i : fileInputMap.get(s)) {
            input.add(i.readData(scanner));
        }
        ret.add(input);
      }
    } catch (RuntimeException e) {
      throw e;
    } finally {
      if (scanner != null) scanner.close();
    }
    return ret;
  }
}
