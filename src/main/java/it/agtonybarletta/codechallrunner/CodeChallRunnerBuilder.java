package it.agtonybarletta.codechallrunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* Builder */
public class CodeChallRunnerBuilder{

  private List<String> files;
  private String currentFile = null;
  private Map<String,List<Input<?>>> fileInputMap;

  public CodeChallRunnerBuilder() {
    this.files = new LinkedList<>();
    this.fileInputMap = new HashMap<>(); 
  }

  public CodeChallRunnerBuilder addFile(String filePrefix) {
    this.files.add(filePrefix);				
    this.currentFile = filePrefix;
    this.fileInputMap.put(filePrefix, new LinkedList<>());
    return this;
  }

  public CodeChallRunnerBuilder addInput(Input<?> input) {
      if(this.currentFile == null){
          throw new RuntimeException("Cannot add input before adding a file. Call addFile then addInput");
      }
      this.fileInputMap.get(this.currentFile).add(input);
      return this;
  }

    public CodeChallRunnerBuilder addOutput(Input<?> output) {
        if(this.currentFile == null){
            throw new RuntimeException("Cannot add input before adding a file. Call addFile then addInput");
        }
        //this.fileInputMap.get(this.currentFile).add(output);
        return this;
    }

    public CodeChallRunner build() {
      return new CodeChallRunner(this.files, this.fileInputMap);
    }
}
