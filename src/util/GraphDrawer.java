package util;

import guru.nidi.graphviz.attribute.Arrow;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Records;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import libs.ASTNode;
import model.Class;
import model.Method;
import model.Parameter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static guru.nidi.graphviz.attribute.Arrow.DirType.BACK;
import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.model.Factory.*;

public class GraphDrawer {
    private final static String METHOD_NEWLINE = "\\l";
    private final static String SPACE = " ";
    private final static String SEPARATOR = "|";
    private final static String ABSTRACT = "\\<\\<Abstract\\>\\>\n";
    private final static String INTERFACE = "\\<\\<Interface\\>\\>\n";
    private final static String PUBLIC_PREFIX = "+";
    private final static String PRIVATE_PREFIX = "-";
    private final static String PROTECTED_PREFIX = "#";

    private MutableGraph graph;

    public GraphDrawer() {

    }

    public void generateGraph() {
        List<Class> classes = ASTNode.getClasses();

        graph = mutGraph("UML Class Diagram").setDirected(true).use((gr, ctx) -> {
            graphAttrs().add(Rank.dir(TOP_TO_BOTTOM));

            for (Class clazz : classes) {
                linkAttrs().add(Arrow.NORMAL.dir(BACK));
                mutNode(clazz.getName());

                if (clazz.getExtendedClass() != null) {
                    mutNode(clazz.getExtendedClass().getName()).addLink(mutNode(clazz.getName()));
                }

                if (clazz.getImplementedClasses() != null) {
                    for (Class implementedClass : clazz.getImplementedClasses()) {
                        linkAttrs().add(Style.DASHED);
                        mutNode(implementedClass.getName()).addLink(mutNode(clazz.getName()));
                    }
                }

                mutNode(clazz.getName()).add(Records.of(recordBuilder(clazz)));

                // Reset link attributes
                linkAttrs().add(Style.SOLID);
            }
        });
    }

    public void export() {
        try {
            Graphviz.fromGraph(graph).height(900).render(Format.PNG).toFile(new File("UML/UML Diagram.png"));
        } catch (IOException e) {
            throw new RuntimeException("Export Failed!");
        }
    }

    private String recordBuilder(Class clazz) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('{');
        if (clazz.getType().equals("abstract")) {
            stringBuilder.append(ABSTRACT);
        } else if (clazz.getType().equals("interface")) {
            stringBuilder.append(INTERFACE);
        }

        stringBuilder.append(clazz.getName());
        stringBuilder.append(SEPARATOR);

        for (Method method : clazz.getMethods()) {
            if (method.getModifier().equals("public")) {
                stringBuilder.append(PUBLIC_PREFIX);
            }
            if (method.getModifier().equals("private")) {
                stringBuilder.append(PRIVATE_PREFIX);
            }
            if (method.getModifier().equals("protected")) {
                stringBuilder.append(PROTECTED_PREFIX);
            }

            stringBuilder.append(SPACE);

            stringBuilder.append(method.getName());

            // Param string
            List<String> paramStrings = new ArrayList<>();
            for (Parameter param : method.getParameters()) {
                StringBuilder sb = new StringBuilder();
                sb.append(param.getName()).append(": ").append(param.getType());
                paramStrings.add(sb.toString());
            }
            String finalParamString = String.join(", ", paramStrings);

            stringBuilder.append('(')
                    .append(finalParamString)
                    .append(");")
                    .append(METHOD_NEWLINE);
        }
        stringBuilder.append('}');

        return stringBuilder.toString();
    }
}
