package libs;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public abstract class ASTNode {
    protected static Tokenizer tokenizer = Tokenizer.getTokenizer();
    static protected PrintWriter writer;
    public static void setWriter(String name) throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(name, "UTF-8");
    }
    public static void closeWriter(){
        writer.close();
    }
    /*
     * Abstract methods
     */
    abstract public void parse();
    abstract public String evaluate();
}