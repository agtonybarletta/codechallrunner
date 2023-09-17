package it.agtonybarletta.codechallrunner;

import it.agtonybarletta.codechallrunner.inputdefinition.InputDefinitionI;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* Builder */
public class CodeChallRunnerBuilder{

  private List<String> inputFiles;
  private String targetFile;
  private String currentFile = null;
  private Map<String,List<InputDefinitionI<?>>> fileInputMap;

  public CodeChallRunnerBuilder() {
    this.inputFiles = new LinkedList<>();
    this.fileInputMap = new HashMap<>(); 
  }

  public CodeChallRunnerBuilder addFile(String filePrefix) {
    this.inputFiles.add(filePrefix);				
    this.currentFile = filePrefix;
    this.fileInputMap.put(filePrefix, new LinkedList<>());
    return this;
  }

  public CodeChallRunnerBuilder addTargetFile(String filePrefix) {
    this.targetFile = filePrefix;
    this.currentFile = filePrefix;
    this.fileInputMap.put(filePrefix, new LinkedList<>());
    return this;
  }

  public CodeChallRunnerBuilder addInput(InputDefinitionI<?> input) {
      if(this.currentFile == null){
          throw new RuntimeException("Cannot add input before adding a file. Call addFile then addInput");
      }
      this.fileInputMap.get(this.currentFile).add(input);
      return this;
  }

    public CodeChallRunnerBuilder addTarget(InputDefinitionI<?> target) {
        if(this.currentFile == null){
            throw new RuntimeException("Cannot add input before adding a file. Call addFile then addInput");
        }
        this.fileInputMap.get(this.currentFile).add(target);
        return this;
    }

    public CodeChallRunner build() {
      return new CodeChallRunner(this.inputFiles, this.fileInputMap, this.targetFile);
    }
}
