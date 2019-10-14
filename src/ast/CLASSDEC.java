package ast;
import libs.SymbolTable;

import java.util.ArrayList;

public class CLASSDEC extends STATEMENT {
    private String className;
    private String classType;
    private RELATION relation = null;

    @Override
    public void parse() {
        if (tokenizer.checkToken("class")) {
            classType = "";
            tokenizer.getAndCheckNext("class");
            className = tokenizer.getNext();
            if (tokenizer.checkToken("extends") || tokenizer.checkToken("implements")) {
                relation = new RELATION();
                relation.parse();
            }
        }

        if (tokenizer.checkToken("abstract")) {
            classType = "\\<\\<Abstract\\>\\>";
            tokenizer.getAndCheckNext("abstract");
            tokenizer.getAndCheckNext("class");
            className = tokenizer.getNext();
            if (tokenizer.checkToken("extends") || tokenizer.checkToken("implements")) {
                relation = new RELATION();
                relation.parse();
            }
        }

        if (tokenizer.checkToken("interface")) {
            classType = "\\<\\<Interface\\>\\>";
            tokenizer.getAndCheckNext("interface");
            className = tokenizer.getNext();
        }

        SymbolTable.currentClass = className;
        ArrayList methods = new ArrayList<String>();
        SymbolTable.methods.put(className, methods);
    }

    @Override
    public String evaluate() {
        SymbolTable.currentClass = className;
        SymbolTable.values.put(className, "");
        SymbolTable.types.put(className, classType);

        if(relation != null)
        {
            relation.setClassName(className);
            relation.evaluate();
        }
        return null;
    }
}