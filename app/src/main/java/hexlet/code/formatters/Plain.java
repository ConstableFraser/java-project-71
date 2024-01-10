package hexlet.code.formatters;

import java.util.HashMap;
import java.util.Map;

public class Plain {

    public static String format(Map<String, HashMap<String, Object>> modelData) {
        StringBuilder output = new StringBuilder();

        for (Map.Entry<String, HashMap<String, Object>> item : modelData.entrySet()) {
            var node = item.getKey();
            var value = item.getValue().get("value1");
            var typeNode = item.getValue().get("type");

            if (typeNode.equals("nochanges")) {
                continue;
            }

            if (typeNode.equals("added")) {
                output.append("Property '").append(node).append("' was ").append("added").append(" with value: ");
                output.append(normalize(value));
            } else if (typeNode.equals("deleted")) {
                output.append("Property '").append(node).append("' was ").append("removed");
            } else { // modified
                output.append("Property '").append(node).append("' was ").append("updated. ");
                var value1 = normalize(value);
                var value2 = normalize(item.getValue().get("value2"));
                output.append("From ").append(value1);
                output.append(" to ").append(value2);
            }
            output.append("\n");
        }
        return output.toString().trim();
    }

    private static String normalize(Object value) {
        if (value.equals("null")) {
            return "null";
        }

        if (value instanceof Boolean || value instanceof Integer) {
            return String.valueOf(value);
        }

        if (value instanceof String) {
            return String.format("'" + value + "'");
        }
        return "[complex value]";
    }
}
