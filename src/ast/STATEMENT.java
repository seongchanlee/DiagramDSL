package ast;

import libs.ASTNode;

public abstract class STATEMENT extends ASTNode {
    protected static STATEMENT getSubStatement() {
        if (tokenizer.checkToken("class") || tokenizer.checkToken("abstract")
                || tokenizer.checkToken("interface")) {
            return new CLASSDEC();
        }

        if (tokenizer.checkToken("public") || tokenizer.checkToken("private")
                || tokenizer.checkToken("protected")) {
            return new METHODDEC();
        }

        // shouldn't reach here
        throw new RuntimeException("Invalid statement detected");
    }
}
