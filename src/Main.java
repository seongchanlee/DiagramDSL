import ast.PROGRAM;
import libs.ASTNode;
import libs.Tokenizer;
import util.GraphDrawer;

import java.util.Arrays;
import java.util.List;

// GraphViz imports

public class Main {
    public static void main(String[] args) {
        List<String> literals = Arrays.asList("public", "private", "protected", "abstract", "interface", "extends", "implements", "class", "Class", "public", "private", "static", "method", "parameters", "return", "param");
        Tokenizer.makeTokenizer("input.txt",literals);
        ASTNode program = new PROGRAM();
        program.parse();
        program.evaluate();

        GraphDrawer graphDrawer = new GraphDrawer();
        graphDrawer.generateGraph();
        graphDrawer.export();
    }
}
