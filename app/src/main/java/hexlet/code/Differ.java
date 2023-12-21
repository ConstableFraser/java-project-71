package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.LinkedHashMap;

public class Differ {
    public static String generate(String filePath1, String filePath2) {
        return Differ.generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) {
        Map<String, Object> dict1 = null;
        Map<String, Object> dict2 = null;
        LinkedHashMap<String, LinkedList<Object>> model = new LinkedHashMap<>();

        try {
            dict1 = Parser.getData(filePath1);
            dict2 = Parser.getData(filePath2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dict1 == null || dict2 == null) {
            var fileError = dict1 == null ? filePath1 : filePath2;
            throw new Error("Error reading file: " + fileError);
        }
        Set<String> keys = new TreeSet<>(dict1.keySet());
        keys.addAll(dict2.keySet());

        for (String node : keys) {
            var value1 = dict1.get(node) == null ? "null" : dict1.get(node);
            var value2 = dict2.get(node) == null ? "null" : dict2.get(node);
            var nodeInfo = new LinkedList<>();

            if (dict1.containsKey(node) && dict2.containsKey(node)) {
                if (value1.equals(value2)) {
                    nodeInfo.add(value1);
                    nodeInfo.add("nochanges");
                    model.put(node, nodeInfo);
                } else {
                    nodeInfo.add(value1);
                    nodeInfo.add("modified");
                    nodeInfo.add(value2);
                    model.put(node, nodeInfo);
                }
            } else if (dict1.containsKey(node)) {
                nodeInfo.add(value1);
                nodeInfo.add("deleted");
                model.put(node, nodeInfo);
            } else {
                nodeInfo.add(value2);
                nodeInfo.add("added");
                model.put(node, nodeInfo);
            }
        }
        return Formatter.print(model, format);
    }
}
