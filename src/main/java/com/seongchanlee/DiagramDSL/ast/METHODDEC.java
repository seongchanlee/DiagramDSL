package com.seongchanlee.DiagramDSL.ast;

import com.seongchanlee.DiagramDSL.libs.ASTNode;
import com.seongchanlee.DiagramDSL.model.Method;

public class METHODDEC extends STATEMENT {
    private String methodName;
    private boolean isAbstract;
    private MODIFIER modifier;
    private KEYWORDS keywords;
    private PARAMETERS parameters;

    @Override
    public void parse() {
        modifier = new MODIFIER();
        modifier.parse();

        if (tokenizer.checkToken("static") || tokenizer.checkToken("final")) {
            keywords = new KEYWORDS();
            keywords.parse();
        }

        if (tokenizer.checkToken("abstract")) {
            tokenizer.getAndCheckNext("abstract");
            isAbstract = true;
        } else {
            isAbstract = false;
        }

        tokenizer.getAndCheckNext("method");
        methodName = tokenizer.getNext();

        if (tokenizer.checkToken("param")) {
            parameters = new PARAMETERS();
            parameters.parse();
        }
    }

    @Override
    public void evaluate() {
        ASTNode.setCurrentMethodName(methodName);
        Method evaluatedMethod = new Method(methodName, isAbstract);
        ASTNode.addMethod(evaluatedMethod);
        ASTNode.getClassObj(ASTNode.getCurrentClassName()).addMethod(evaluatedMethod);

        modifier.evaluate();
        if (keywords != null) {
            keywords.evaluate();
        }
        if (parameters != null) {
            parameters.evaluate();
        }
    }
}
