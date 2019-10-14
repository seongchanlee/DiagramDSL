package ast;

import libs.ASTNode;

public class MODIFIER extends STATEMENT {
    private String modifier;

    @Override
    public void parse() {
        modifier = tokenizer.getNext();
    }

    @Override
    public void evaluate() {
        ASTNode.getMethodObj(ASTNode.getCurrentMethodName()).setModifier(modifier);
    }
}
