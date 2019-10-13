package ast;

public class CLASSDEC extends STATEMENT {
    private String className;
    private String classType;
    private RELATION relation;

    @Override
    public void parse() {
        if (tokenizer.checkToken("class")) {
            classType = "regular";
            tokenizer.getAndCheckNext("class");
            className = tokenizer.getNext();
            if (tokenizer.checkToken("extends") || tokenizer.checkToken("implements")) {
                relation = new RELATION();
                relation.parse();
            }
        }

        if (tokenizer.checkToken("abstract")) {
            classType = "abstract";
            tokenizer.getAndCheckNext("abstract");
            tokenizer.getAndCheckNext("class");
            className = tokenizer.getNext();
            if (tokenizer.checkToken("extends") || tokenizer.checkToken("implements")) {
                relation = new RELATION();
                relation.parse();
            }
        }

        if (tokenizer.checkToken("interface")) {
            classType = "interface";
            tokenizer.getAndCheckNext("interface");
            className = tokenizer.getNext();
        }
    }

    @Override
    public String evaluate() {
        return null;
    }

    public String getClassType() {
        return classType;
    }
}
