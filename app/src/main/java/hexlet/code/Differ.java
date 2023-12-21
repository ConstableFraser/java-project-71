package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.List;
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
            var typeOfNode = getTypeOfNode(dict1, dict2, node);
            makeNodeInfo(nodeInfo, value1, typeOfNode, value2);
            model.put(node, nodeInfo);
        }
        return Formatter.print(model, format);
    }

    private static String getTypeOfNode(Map<String, Object> dict1, Map<String, Object> dict2, String node) {
        boolean inDict1Present = dict1.containsKey(node);
        boolean inDict2Present = dict2.containsKey(node);
        var value1 = dict1.get(node) == null ? "null" : dict1.get(node);
        var value2 = dict2.get(node) == null ? "null" : dict2.get(node);

        if (inDict1Present && inDict2Present) {
            return value1.equals(value2) ? "nochanges" : "modified";
        }

        if (inDict1Present) {
            return "deleted";
        }
        return "added";
    }

    private static void makeNodeInfo(List<Object> nodeInfo, Object value1, String typeOfNode, Object value2) {
        switch (typeOfNode) {
            case "modified":
                nodeInfo.add(value1);
                nodeInfo.add(typeOfNode);
                nodeInfo.add(value2);
                break;
            case "added":
                nodeInfo.add(value2);
                nodeInfo.add(typeOfNode);
                break;
            case "deleted":
            case "nochanges":
                nodeInfo.add(value1);
                nodeInfo.add(typeOfNode);
                break;
            default:
                throw new Error("Unexpected type of node: " + typeOfNode);
        }
    }
}
