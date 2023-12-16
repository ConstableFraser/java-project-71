package hexlet.code;

import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;

import java.util.LinkedList;
import java.util.Map;

public class Formatter {
    public static String print(Map<String, LinkedList<Object>> modelData, String format) {
        String diff = "";

        switch (format) {
            case "stylish":
                diff = Stylish.format(modelData);
                break;
            case "plain":
                diff = Plain.format(modelData);
                break;
            case "json":
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + format);
        }
        return diff;
    }
}
