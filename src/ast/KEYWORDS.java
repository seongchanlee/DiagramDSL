package ast;

public class KEYWORDS extends STATEMENT {
    private boolean isStatic = false;
    private boolean isFinal = false;

    @Override
    public void parse() {
        if (tokenizer.checkToken("static")) {
            tokenizer.getAndCheckNext("static");
            isStatic = true;
        }

        if (tokenizer.checkToken("final")) {
            tokenizer.getAndCheckNext("final");
            isFinal = true;
        }
    }

    @Override
    public String evaluate() {
        String result = "";
        if(isFinal){ result += "final "; }
        if(isStatic){ result += "static "; }
        return result;
    }
}
