package ast;

import libs.SymbolTable;

import java.util.ArrayList;
import java.util.List;

public class RELATION extends STATEMENT {
    private String extendedClass;
    private List<String> implementedClass = new ArrayList<>();
    private String nodeName;

    @Override
    public void parse() {
        if (tokenizer.checkToken("extends")) {
            assert extendedClass == null;
            tokenizer.getAndCheckNext("extends");
            extendedClass = tokenizer.getNext();
            if(SymbolTable.relations.get(extendedClass) == null){
                ArrayList relations = new ArrayList<String>();
                SymbolTable.relations.put(extendedClass, relations);
            }
        }

        if (tokenizer.checkToken("implements")) {
            tokenizer.getAndCheckNext("implements");
            String imClass = tokenizer.getNext();
            implementedClass.add(imClass);
            if(SymbolTable.relations.get(imClass) == null){
                ArrayList relations = new ArrayList<String>();
                SymbolTable.relations.put(imClass, relations);
            }
            while (tokenizer.checkToken(",")) {
                tokenizer.getAndCheckNext(",");
                imClass = tokenizer.getNext();
                implementedClass.add(imClass);
                if(SymbolTable.relations.get(imClass) == null){
                    ArrayList relations = new ArrayList<String>();
                    SymbolTable.relations.put(imClass, relations);
                }
            }
        }
    }

    @Override
    public String evaluate()
    {
        // check extended or implemented classes exist
        if(extendedClass != null && !SymbolTable.values.get(extendedClass).equals(null))
        {
            // build graphviz connections
            String extendsArrow = "to(" + nodeName + ").with(Arrow.NORMAL.dir(BACK))";
            SymbolTable.relations.get(extendedClass).add(extendsArrow);
        }
        for(String imClass: implementedClass)
        {
            if(imClass != null && !SymbolTable.values.get(imClass).equals(null))
            {
                String implementsArrow = "to(" + nodeName + ").with(Arrow.DASHED.dir(BACK)).with(Style.DASHED)";
                SymbolTable.relations.get(imClass).add(implementsArrow);
            }
        }
        return null;
    }

    public void setClassName(String node)
    {
        nodeName = node;
    }
}
