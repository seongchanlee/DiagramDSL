package ast;

import libs.ASTNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RELATION extends STATEMENT {
    private String extendedClass;
    private List<String> implementedClasses = new ArrayList<>();

    @Override
    public void parse() {
        if (tokenizer.checkToken("extends")) {
            assert extendedClass == null;
            tokenizer.getAndCheckNext("extends");
            extendedClass = tokenizer.getNext();
        }

        if (tokenizer.checkToken("implements")) {
            tokenizer.getAndCheckNext("implements");
            String implementedClassStrings = tokenizer.getNext();
            List<String> implementedClassStringList = Arrays.asList(implementedClassStrings.split(","));
            implementedClasses.addAll(implementedClassStringList);
        }
    }

    @Override
    public void evaluate() {
        if (extendedClass != null) {
            ASTNode.getClassObj(ASTNode.getCurrentClassName()).classExtends(ASTNode.getClassObj(extendedClass));
        }

        if (implementedClasses.size() > 0) {
            for (String clazz : implementedClasses) {
                ASTNode.getClassObj(ASTNode.getCurrentClassName()).classImplements(ASTNode.getClassObj(clazz));
            }
        }
    }
}
