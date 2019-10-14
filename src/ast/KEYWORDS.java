package ast;

import libs.ASTNode;

public class KEYWORDS extends STATEMENT {
    private boolean isStatic = false;
    private boolean isFinal = false;

    @Override
    public void parse() {
        if (tokenizer.checkToken("static")) {
            tokenizer.getAndCheckNext("static");
            isStatic = true;
        }

        if (tokenizer.checkToken("final")) {
            tokenizer.getAndCheckNext("final");
            isFinal = true;
        }
    }

    @Override
    public void evaluate() {
        if (isStatic) {
            ASTNode.getMethodObj(ASTNode.getCurrentMethodName()).setStatic();
        }

        if (isFinal) {
            ASTNode.getMethodObj(ASTNode.getCurrentMethodName()).setFinal();
        }
    }
}
