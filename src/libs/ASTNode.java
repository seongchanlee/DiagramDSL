package libs;

import model.Class;
import model.Method;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ASTNode {
    protected static Tokenizer tokenizer = Tokenizer.getTokenizer();
    static protected PrintWriter writer;
    public static void setWriter(String name) throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(name, "UTF-8");
    }
    public static void closeWriter(){
        writer.close();
    }

    private static Map<String, Class> evaluatedClasses = new HashMap<>();
    private static Map<String, Method> evaluatedMethods = new HashMap<>();
    private static String currentClassName;
    private static String currentMethodName;

    public static void addClass(Class newClass) {
        evaluatedClasses.put(newClass.getName(), newClass);
    }

    public static void addMethod(Method newMethod) {
        evaluatedMethods.put(newMethod.getName(), newMethod);
    }

    public static Class getClassObj(String className) {
        return evaluatedClasses.get(className);
    }

    public static Method getMethodObj(String methodName) {
        return evaluatedMethods.get(methodName);
    }

    public static void setCurrentClassName(String className) {
        currentClassName = className;
    }

    public static String getCurrentClassName() {
        return currentClassName;
    }

    public static void setCurrentMethodName(String methodName) {
        currentMethodName = methodName;
    }

    public static String getCurrentMethodName() {
        return currentMethodName;
    }

    public static List<Class> getClasses() {
        return new ArrayList<>(evaluatedClasses.values());
    }

    /*
     * Abstract methods
     */
    abstract public void parse();
    abstract public void evaluate();
}
