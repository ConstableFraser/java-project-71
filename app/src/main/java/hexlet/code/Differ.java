package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String format, String filePath1, String filePath2) {
        Map<String, Object> dict1 = null;
        Map<String, Object> dict2 = null;
        final String indent = "  ";

        try {
            dict1 = Parser.getData(filePath1);
            dict2 = Parser.getData(filePath2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<String> keys = new TreeSet<>(dict1.keySet());
        keys.addAll(dict2.keySet());
        StringBuilder result = new StringBuilder();
        result.append("{");
        result.append(System.getProperty("line.separator"));

        for (String key : keys) {
            if (dict1.containsKey(key) && dict2.containsKey(key)) {
                if (dict1.get(key).equals(dict2.get(key))) {
                    result.append(indent + "  ").append(key).append(": ").append(dict1.get(key));
                } else {
                    result.append(indent + "- ").append(key).append(": ").append(dict1.get(key));
                    result.append(System.getProperty("line.separator"));
                    result.append(indent + "+ ").append(key).append(": ").append(dict2.get(key));
                }
            } else if (dict1.containsKey(key)) {
                result.append(indent + "- ").append(key).append(": ").append(dict1.get(key));
            } else {
                result.append(indent + "+ ").append(key).append(": ").append(dict2.get(key));
            }
            result.append(System.getProperty("line.separator"));
        }
        return result.append("}").toString();
    }
}
