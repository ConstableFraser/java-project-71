package hexlet.code.formatters;

import java.util.HashMap;
import java.util.Map;

public class Plain {

    public static String format(Map<String, HashMap<String, Object>> modelData) {
        String output = "";

        for (Map.Entry<String, HashMap<String, Object>> item : modelData.entrySet()) {
            var node = item.getKey();
            var value = item.getValue().get("value1");
            var typeNode = item.getValue().get("type");

            if (typeNode.equals("nochanges")) {
                continue;
            }

            if (typeNode.equals("added")) {
                output += "Property '" + node + "' was " + "added" + " with value: ";
                output += normalize(value);
            } else if (typeNode.equals("deleted")) {
                output += "Property '" + node + "' was " + "removed";
            } else { // modified
                output += "Property '" + node + "' was " + "updated. ";
                var value1 = normalize(value);
                var value2 = normalize(item.getValue().get("value2"));
                output += "From " + value1;
                output += " to " + value2;
            }
            output += "\n";
        }
        return output.trim();
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
