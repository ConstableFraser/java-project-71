package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    public static Map<String, Object> getData(String content, String format) throws Error, Exception {
        return switch (format) {
            case "json" -> new ObjectMapper().readValue(content, new TypeReference<>() { });
            case "yaml", "yml" -> new YAMLMapper().readValue(content, new TypeReference<>() { });
            default -> throw new Error("Unknown file extension: " + format);
        };
    }
}
