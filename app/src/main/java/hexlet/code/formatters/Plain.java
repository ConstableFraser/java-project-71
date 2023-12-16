package hexlet.code.formatters;

import java.util.LinkedList;
import java.util.Map;

public class Plain {

    public static String format(Map<String, LinkedList<Object>> modelData) {
        StringBuilder output = new StringBuilder();

        for (Map.Entry<String, LinkedList<Object>> item : modelData.entrySet()) {
            var node = item.getKey();
            var value = item.getValue().get(0);
            var type = item.getValue().get(1);

            if (type.equals("nochanges")) {
                continue;
            }

            output.append("Property '").append(node).append("' was ");

            if (type.equals("added")) {
                output.append("added").append(" with value: ");
                output.append(normalize(value));
            } else if (type.equals("deleted")) {
                output.append("removed");
            } else if (type.equals("modified")) {
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
        } else if (value instanceof Boolean) {
            return String.valueOf(value);
        } else if (value instanceof Integer) {
            return String.valueOf(value);
        } else if (value instanceof String) {
            return String.format("'" + value + "'");
        }
        return "[complex value]";
    }
}
