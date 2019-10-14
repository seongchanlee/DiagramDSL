package libs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    public static String currentClass;
    public static Map<String, String>            values    = new HashMap<>();
    public static Map<String, String>            types     = new HashMap<>();
    public static Map<String, ArrayList<String>> relations = new HashMap<>();
    public static Map<String, ArrayList<String>> methods   = new HashMap<>();
}
