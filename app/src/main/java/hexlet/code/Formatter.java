package hexlet.code;

import java.util.LinkedList;
import java.util.Map;

public class Formatter {
    private static final String ADDED = "+";
    private static final  String DELETED = "-";
    private static final String NOCHANGES = " ";

    public static String print(Map<String, LinkedList<Object>> modelData, String format) {
        switch (format) {
            case "stylish":
                return stylish(modelData);
            case "plain":
                //TODO
                break;
            case "json":
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + format);
        }
        return ""; //DELETE!
    }
    private static String stylish(Map<String, LinkedList<Object>> modelData) {
        final String indent = "  ";
        StringBuilder output = new StringBuilder();
        output.append("{");

        for (Map.Entry<String, LinkedList<Object>> item : modelData.entrySet()) {
            var node = item.getKey();
            var value = item.getValue().get(0);
            var type = item.getValue().get(1);

            output.append(System.lineSeparator());
            output.append(indent);

            if (type.equals("nochanges")) {
                output.append(NOCHANGES);
            } else if (type.equals("added")) {
                output.append(ADDED);
            } else if (type.equals("deleted")) {
                output.append(DELETED);
            } else if (type.equals("modified")) {
                output.append(DELETED).append(" ").append(node).append(": ").append(value);
                output.append(System.lineSeparator());
                output.append(indent);
                output.append(ADDED).append(" ").append(node).append(": ").append(item.getValue().get(2));
                continue;
            }
            output.append(" ").append(node).append(": ").append(value);
        }
        output.append(System.lineSeparator());
        return output.append("}").toString();
    }
}
