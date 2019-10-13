package libs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import libs.Node;

public class SymbolTable {
    public static Map<String, String>            values    = new HashMap<>();
    public static Map<String, String>            types     = new HashMap<>();
    public static Map<String, Node>              relations = new HashMap<>();
    public static Map<String, ArrayList<String>> methods   = new HashMap<>();
}
