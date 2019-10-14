import ast.PROGRAM;
import libs.ASTNode;
import libs.Tokenizer;
import libs.SymbolTable;

import java.util.Arrays;
import java.util.List;

import java.io.File;
import java.io.IOException;

// GraphViz imports
import guru.nidi.graphviz.attribute.Arrow;
import guru.nidi.graphviz.attribute.Records;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import static guru.nidi.graphviz.attribute.Arrow.DirType.BACK;
import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.attribute.Rank.dir;
import static guru.nidi.graphviz.attribute.Records.rec;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

public class Main {
    public static void main(String[] args) {
        List<String> literals = Arrays.asList("public", "private", "protected", "abstract", "interface", "extends", "implements", "class", "Class", "public", "private", "static", "method", "parameters", "return", "param");
        Tokenizer.makeTokenizer("input.txt",literals);
        ASTNode program = new PROGRAM();
        program.parse();
        program.evaluate();

        // Build UML diagram



    }
}
