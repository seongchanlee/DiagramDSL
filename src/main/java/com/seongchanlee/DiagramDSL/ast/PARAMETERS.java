package com.seongchanlee.DiagramDSL.ast;

import com.seongchanlee.DiagramDSL.libs.ASTNode;
import com.seongchanlee.DiagramDSL.model.Parameter;

import java.util.ArrayList;
import java.util.List;

public class PARAMETERS extends STATEMENT {
    private String tempParamString;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("param");
        tempParamString = tokenizer.getNext();
    }

    @Override
    public void evaluate() {
        String[] paramStrings = tempParamString.split(",");

        List<Parameter> parameters = new ArrayList<>();

        for(int i = 0; i < paramStrings.length; i++) {
            String[] splitParam = paramStrings[i].split(":");
            Parameter parameter = new Parameter(splitParam[0], splitParam[1]);
            parameters.add(parameter);
        }

        ASTNode.getMethodObj(ASTNode.getCurrentMethodName()).setParameters(parameters);
    }
}
