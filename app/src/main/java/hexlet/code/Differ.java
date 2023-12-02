package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String format, String filePath1, String filePath2) {
        Map<String, Object> dict1 = null;
        Map<String, Object> dict2 = null;
        final String indent = "  ";

        try {
            dict1 = getData(filePath1);
            dict2 = getData(filePath2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Set<String> keys = new TreeSet<>(dict1.keySet());
        keys.addAll(dict2.keySet());
        StringBuilder result = new StringBuilder();

        for (String key : keys) {
            if (dict1.containsKey(key) && dict2.containsKey(key)) {
                if (dict1.get(key).equals(dict2.get(key))) {
                    result.append(indent + "  ").append(key).append(": ").append(dict1.get(key));
                } else {
                    result.append(indent + "- ").append(key).append(": ").append(dict1.get(key));
                    result.append("\\n");
                    result.append(indent + "+ ").append(key).append(": ").append(dict2.get(key));
                }
            } else if (dict1.containsKey(key)) {
                result.append(indent + "- ").append(key).append(": ").append(dict1.get(key));
            } else {
                result.append(indent + "+ ").append(key).append(": ").append(dict2.get(key));
            }
            result.append("\\n");
        }
        return "{\\n".concat(String.valueOf(result)).concat("}\\n");
    }

    public static Map<String, Object> getData(String readFilePath) throws Exception {
        Path path = Paths.get(readFilePath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        String json = Files.readString(path);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, new TypeReference<>() { });
    }
}
