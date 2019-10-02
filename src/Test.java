import guru.nidi.graphviz.attribute.Arrow;
import guru.nidi.graphviz.attribute.Records;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.Node;

import java.io.File;
import java.io.IOException;

import static guru.nidi.graphviz.attribute.Arrow.DirType.BACK;
import static guru.nidi.graphviz.attribute.Rank.RankDir.TOP_TO_BOTTOM;
import static guru.nidi.graphviz.attribute.Rank.dir;
import static guru.nidi.graphviz.attribute.Records.rec;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

public class Test {

    public static void main(String[] args){

        Node node0 = node("node0").with(Records.of("{ \\<\\<abstract\\>\\>\nTeacher| teach();}"));
        Node node1 = node("node1").with(Records.of(rec("BiologyTeacher")));
        Node node2 = node("node2").with(Records.of(rec("PhysicsTeacher")));

        Graph g = graph("example1").directed()
                .graphAttr().with(dir(TOP_TO_BOTTOM)).with(
                        node0.link(
                                to(node1).with(Arrow.NORMAL.dir(BACK)),
                                to(node2).with(Arrow.NORMAL.dir(BACK))));
        try {
            Graphviz.fromGraph(g).height(900).render(Format.PNG).toFile(new File("example/ex1.png"));
        } catch (IOException e) {
            throw new RuntimeException("xd");
        }
    }
}
