package ast;

import libs.ASTNode;

import java.util.ArrayList;
import java.util.List;

public class PROGRAM extends ASTNode {
    private List<STATEMENT> statements = new ArrayList<>();

    @Override
    public void parse() {
        while (tokenizer.moreTokens()) {
            STATEMENT s = STATEMENT.getSubStatement();
            s.parse();
            statements.add(s);
        }
    }

    @Override
    public void evaluate() {
        for(STATEMENT s: statements){
            s.evaluate();
        }
    }
}
