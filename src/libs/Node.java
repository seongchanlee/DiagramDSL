package libs;

public abstract class Node {
    protected static Tokenizer tokenizer = Tokenizer.getTokenizer();
    // need to connect to renderer.

    /*
     * Abstract methods
     */
    abstract public void parse();
    abstract public String evaluate();
}
