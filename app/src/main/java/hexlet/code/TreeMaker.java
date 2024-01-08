package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class TreeMaker {
    public static Map<String, HashMap<String, Object>> run(Map<String, Object> dict1, Map<String, Object> dict2) {
        Map<String, HashMap<String, Object>> model = new LinkedHashMap<>();

        Set<String> keys = new TreeSet<>(dict1.keySet());
        keys.addAll(dict2.keySet());

        for (String node : keys) {
            var value1 = checkForNull(dict1.get(node));
            var value2 = checkForNull(dict2.get(node));
            var nodeInfo = new HashMap<String, Object>();
            var typeOfNode = getTypeOfNode(dict1, dict2, node);
            makeNodeInfo(nodeInfo, value1, typeOfNode, value2);
            model.put(node, nodeInfo);
        }
        return model;
    }
    private static String getTypeOfNode(Map<String, Object> dict1, Map<String, Object> dict2, String node) {
        boolean inDict1Present = dict1.containsKey(node);
        boolean inDict2Present = dict2.containsKey(node);
        var value1 = checkForNull(dict1.get(node));
        var value2 = checkForNull(dict2.get(node));

        if (inDict1Present && inDict2Present) {
            return value1.equals(value2) ? "nochanges" : "modified";
        }
        return inDict1Present ? "deleted" : "added";
    }

    private static void makeNodeInfo(Map<String, Object> nodeInfo, Object value1, String typeOfNode, Object value2) {
        switch (typeOfNode) {
            case "modified":
                nodeInfo.put("value1", value1);
                nodeInfo.put("type", typeOfNode);
                nodeInfo.put("value2", value2);
                break;
            case "added":
                nodeInfo.put("value1", value2);
                nodeInfo.put("type", typeOfNode);
                break;
            case "deleted":
            case "nochanges":
                nodeInfo.put("value1", value1);
                nodeInfo.put("type", typeOfNode);
                break;
            default:
                throw new Error("Unexpected type of node: " + typeOfNode);
        }
    }

    private static Object checkForNull(Object o) {
        return o == null ? "null" : o;
    }
}
