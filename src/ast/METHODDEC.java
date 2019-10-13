package ast;

import java.util.ArrayList;
import java.util.List;

public class METHODDEC extends STATEMENT {
    private String methodName;
    private String methodType;
    private String modifier;
    private List<PARAMETER> parameters = new ArrayList<>();

    @Override
    public void parse() {
        MODIFIER modifier = new MODIFIER();
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
        while (tokenizer.checkToken("param")) {
            tokenizer.getAndCheckNext("param");
            PARAMETER parameter = new PARAMETER();
            parameter.parse();
            parameters.add(parameter);
        }
    }

    @Override
    public String evaluate() {
        return null;
    }
}
