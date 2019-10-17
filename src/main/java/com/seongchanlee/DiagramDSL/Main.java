package com.seongchanlee.DiagramDSL;

import com.seongchanlee.DiagramDSL.ast.PROGRAM;
import com.seongchanlee.DiagramDSL.libs.ASTNode;
import com.seongchanlee.DiagramDSL.libs.Tokenizer;
import com.seongchanlee.DiagramDSL.util.GraphDrawer;

import java.util.Arrays;
import java.util.List;

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
