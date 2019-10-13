import ast.PROGRAM;
import libs.Node;
import libs.Tokenizer;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> literals = Arrays.asList("Public", "Private", "Protected", "abstract", "interface", "extends", "implements", "class", "Class", "public", "private", "static", "method", "parameters", "return", "param");
        Tokenizer.makeTokenizer("input.txt",literals);
        Node program = new PROGRAM();
        program.parse();
    }
}
