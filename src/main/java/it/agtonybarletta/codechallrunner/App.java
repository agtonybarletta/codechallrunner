package it.agtonybarletta.codechallrunner;

/** Hello world! */
public class App {
  public static void main(String[] args) {
    /*
    System.out.println("Hello World!");
    CodeChallRunner runner = new CodeChallRunner();
    /*runner.addFile("input1") // means get all input1.<testNumber>.txt
    // .addlines("") // means add 1 input as a list of Strings until parsing "" line
    .addInput(new StringInput("<EOI>"))
    .addInput(new StringInput("\\n"))
    .addInput(new ListInput<String>("<EOF>", "\\n", "".getClass()))
    * /
    runner
        .addFile("input3")
        .addInput(new ListInput<>(Input.NEW_LINE, " ", "".getClass()))
        // .addInput(new StringInput(Input.NEW_LINE))
        .parse()
        .test(null);
  */
  }
}
