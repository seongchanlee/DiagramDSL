package ast;

import libs.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class METHODDEC extends STATEMENT {
    private String methodName;
    private String methodType;
    private MODIFIER modifier;
    private List<PARAMETER> parameters = new ArrayList<>();

    @Override
    public void parse() {
        modifier = new MODIFIER();
        modifier.parse();

        if (tokenizer.checkToken("static") || tokenizer.checkToken("final")) {
            KEYWORDS keywords = new KEYWORDS();
            keywords.parse();
        }

        if (tokenizer.checkToken("abstract")) {
            methodType = tokenizer.getNext();
        } else {
            methodType = "regular";
        }

        tokenizer.getAndCheckNext("method");
        methodName = tokenizer.getNext();
        SymbolTable.methods.get(SymbolTable.currentClass).add(methodName);

        while (tokenizer.checkToken("param")) {
            tokenizer.getAndCheckNext("param");
            PARAMETER parameter = new PARAMETER();
            parameter.parse();
            parameters.add(parameter);
        }
    }

    @Override
    public String evaluate() {
        String method = "";
        String modifierString = modifier.evaluate();
        String type = methodType.equals("regular") ? " " : " " + methodType + " ";
        method += modifierString + type + methodName;
        if(parameters.isEmpty())
        {
            method += "()";
        }
        else
        {
            int count = 0;
            method += "(";
            for(PARAMETER p: parameters)
            {
                if(count != 0){ method += ", "; }
                method += p.evaluate();
                count++;
            }
            method += ")";
        }
        // Add method to current class
        String activeClass = SymbolTable.currentClass;
        SymbolTable.methods.get(activeClass).add(method);
        System.out.println(method);
        return method;
    }
}
