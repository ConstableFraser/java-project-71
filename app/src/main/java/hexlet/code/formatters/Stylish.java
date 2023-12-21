package hexlet.code.formatters;

import java.util.LinkedList;
import java.util.Map;

public class Stylish {
    private static final String ADDED = "+";
    private static final  String DELETED = "-";
    private static final String NOCHANGES = " ";

    public static String format(Map<String, LinkedList<Object>> modelData) {
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
            } else {
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
