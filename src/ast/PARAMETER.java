package ast;

public class PARAMETER extends STATEMENT {
    private String parameterName;

    @Override
    public void parse() {
        parameterName = tokenizer.getNext();
    }

    @Override
    public String evaluate() {
        return null;
    }
}
