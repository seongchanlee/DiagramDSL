import ast.PROGRAM;
import libs.ASTNode;
import libs.Tokenizer;
import libs.SymbolTable;

import java.util.*;

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
        String nodeDecl = "";
        Map<String, Node> nodes = new HashMap<>();
        Iterator classes = SymbolTable.values.entrySet().iterator();
        while (classes.hasNext())
        {
            // create boxes for each class
            Map.Entry pair = (Map.Entry)classes.next();
            String className = (String) pair.getKey();
            String classType = SymbolTable.types.get(className);
            ArrayList<String> methods = SymbolTable.methods.get(className);
            String record = "{" + (!classType.equals("") ? (classType + "\n") : "") + className;
            for (String method : methods){
                record += "| " + method + ";";
            }
            record += "}";
            nodeDecl += "Node " + className + " = " + "node(\"" + className + "\").with(Records.of(\"" + record + "\"));\n";
            nodes.put(className, node(className).with(Records.of(record)));
        }

        // create links between nodes
        String output = "";
        Iterator relations = SymbolTable.relations.entrySet().iterator();
        int connectionCount = 0;
        while (relations.hasNext()){
            String connection = "";
            Map.Entry pair = (Map.Entry)relations.next();
            String className = (String) pair.getKey();
            ArrayList<String> links = SymbolTable.relations.get(className);
            int linkCount = 0;
            if (links != null){
                connection += className + ".link(";
                for (String link : links){
                    if(linkCount == 0){
                        connection += link;
                    }
                    else
                    {
                        connection += "," + link;
                    }
                    linkCount++;
                }
                connection += ")";
                if(connectionCount == 0)
                {
                    output += connection;
                }
                else
                {
                    output += "," + connection;
                }
                connectionCount++;
            }
        }

        String uml = nodeDecl + "\n" + "Graph g = graph(\"example1\").directed().graphAttr()" +
                      ".with(dir(TOP_TO_BOTTOM)).with(" + output + ");";
        System.out.println(uml);
    }
}
