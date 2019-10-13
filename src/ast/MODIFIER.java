package ast;

public class MODIFIER extends STATEMENT {
    private String modifier;

    @Override
    public void parse() {
        modifier = tokenizer.getNext();
    }

    @Override
    public String evaluate() {
        return null;
    }
}
