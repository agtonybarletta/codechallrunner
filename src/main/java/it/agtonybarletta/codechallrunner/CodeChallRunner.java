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
	private String currentFile = null;
	private Map<String,List<Input>> fileInputMap;
	private Input output;
	private List<Input> inputs;

	public CodeChallRunner() {

			this.files = new LinkedList<>();
			this.fileInputMap = new HashMap<>();
			this.inputs = new LinkedList<>();
	}

	public CodeChallRunner addFile(String filePrefix) {
		this.files.add(filePrefix);				
		this.currentFile = filePrefix;
		this.fileInputMap.put(filePrefix, new LinkedList<>());
		return this;
	}

	public CodeChallRunner addInput(Input input) {
		//assert currentFile != null;
		//System.out.println(" adding input: " + input.toString());
		if(this.currentFile == null){
			throw new RuntimeException("Cannot add input before adding a file. Call addFile then addInput");
		}
		this.fileInputMap.get(this.currentFile).add(input);
		this.inputs.add(input);
		return this;
	}

	public CodeChallRunner addOutput(Input output) {
		//assert currentFile != null;
		//System.out.println(" adding input: " + input.toString());
		if(this.currentFile == null){
			throw new RuntimeException("Cannot add input before adding a file. Call addFile then addInput");
		}
		this.fileInputMap.get(this.currentFile).add(output);
		this.output = output;
		return this;
	}

	public CodeChallRunner parse(){
		// TODO: check if there is at least 1 input
			for(String s : files){
				//File myObj = new File(s + ".0.txt");
				InputStream inputFile = this.getClass().getClassLoader().getResourceAsStream(s + ".0.txt");
				//System.out.println(inputFile.toString());
				Scanner myReader = new Scanner(inputFile);//new Scanner(myObj);
				for ( Input i : fileInputMap.get(s))
					i.readData(myReader);
					
				myReader.close();
			}
		return this;
	}

	public boolean test(Function<Object[],Object> runnerFunction){
		//System.out.println("input: " + i.getData());
		Object output = runnerFunction.apply(this.getInput().toArray());
		return this.getOutput().equals(output);

	}

	public List<Object> getInput(){
		//System.out.println(this.inputs.toString());
		return this.inputs.stream().map(i -> i.getData()).collect(Collectors.toList());
	}
	public Object getOutput(){
		//System.out.println(this.inputs.toString());
		return this.output.getData();
	}

}
