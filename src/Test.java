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

        Node ILPScheduler = node("ILPScheduler").with(Records.of("{ILPScheduler| - scheduleA(vmRequest, time, date);}"));
        Node HeuristicScheduler = node("HeuristicScheduler").with(Records.of("{HeuristicScheduler| # scheduleB(vmRequest);}"));
        Node Scheduler = node("Scheduler").with(Records.of("{\\<\\<Abstract\\>\\>\nScheduler| + abstract schedule(vmRequest);| + showPhysicalNetwork();}"));
        Node HybridScheduler = node("HybridScheduler").with(Records.of("{HybridScheduler| + scheduleC;}"));

        Graph g = graph("example1").directed().graphAttr().with(dir(TOP_TO_BOTTOM)).with(HeuristicScheduler.link(to(HybridScheduler).with(Arrow.NORMAL.dir(BACK))),Scheduler.link(to(ILPScheduler).with(Arrow.NORMAL.dir(BACK)),to(HeuristicScheduler).with(Arrow.NORMAL.dir(BACK))));

        try {
            Graphviz.fromGraph(g).height(900).render(Format.PNG).toFile(new File("example/ex1.png"));
        } catch (IOException e) {
            throw new RuntimeException("xd");
        }
    }
}
