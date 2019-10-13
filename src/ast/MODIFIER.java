package ast;

public class MODIFIER extends STATEMENT {
    private String modifier;

    @Override
    public void parse() {
        modifier = tokenizer.getNext();
    }

    @Override
    public String evaluate() {
        if(modifier.equals("public")){
            return "+";
        }
        if(modifier.equals("private")){
            return "-";
        }
        if(modifier.equals("protected")){
            return "#";
        }
        // default is public modifier
        return "+";
    }
}
