package hexlet.code.formatters;

import java.util.LinkedList;
import java.util.Map;

public class Plain {

    public static String format(Map<String, LinkedList<Object>> modelData) {
        StringBuilder output = new StringBuilder();

        for (Map.Entry<String, LinkedList<Object>> item : modelData.entrySet()) {
            var node = item.getKey();
            var value = item.getValue().get(0);
            var typeNode = item.getValue().get(1);

            if (typeNode.equals("nochanges")) {
                continue;
            }

            output.append("Property '").append(node).append("' was ");

            if (typeNode.equals("added")) {
                output.append("added").append(" with value: ");
                output.append(normalize(value));
            } else if (typeNode.equals("deleted")) {
                output.append("removed");
            } else {
                output.append("updated. ");
                var value1 = normalize(value);
                var value2 = normalize(item.getValue().get(2));
                output.append("From ").append(value1);
                output.append(" to ").append(value2);
            }
            output.append(System.lineSeparator());
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