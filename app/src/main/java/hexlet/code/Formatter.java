package hexlet.code;

import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Json;

import java.util.LinkedList;
import java.util.Map;

public class Formatter {
    public static String print(Map<String, LinkedList<Object>> modelData, String format) {
        return switch (format) {
            case "stylish" -> Stylish.format(modelData);
            case "plain" -> Plain.format(modelData);
            case "json" -> Json.format(modelData);
            default -> throw new Error("Unexpected value: " + format);
        };
    }
}
