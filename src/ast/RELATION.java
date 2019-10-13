package ast;

import java.util.ArrayList;
import java.util.List;

public class RELATION extends STATEMENT {
    private String extendedClass;
    private List<String> implementedClass = new ArrayList<>();

    @Override
    public void parse() {
        if (tokenizer.checkToken("extends")) {
            assert extendedClass == null;
            tokenizer.getAndCheckNext("extends");
            extendedClass = tokenizer.getNext();
        }

        if (tokenizer.checkToken("implements")) {
            tokenizer.getAndCheckNext("implements");
            implementedClass.add(tokenizer.getNext());
            while (tokenizer.checkToken(",")) {
                tokenizer.getAndCheckNext(",");
                implementedClass.add(tokenizer.getNext());
            }
        }
    }

    @Override
    public String evaluate() {
        return null;
    }
}
