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
    public static void generate(String format, String filePath1, String filePath2) {
        // System.out.println("result: " + format + " " + filePath1 + " " + filePath2);
        Map<String, Object> dict1 = null;
        Map<String, Object> dict2 = null;
        final String INDENT = "  ";

        try {
            dict1 = getData(filePath1);
            dict2 = getData(filePath2);
        } catch (Exception e){
            e.printStackTrace();
        }
        Set<String> keys = new TreeSet<>(dict1.keySet());
        keys.addAll(dict2.keySet());
        StringBuilder result = new StringBuilder();

        for (String key : keys) {
            if (dict1.containsKey(key) && dict2.containsKey(key)){
                if (dict1.get(key).equals(dict2.get(key))) {
                    result.append(INDENT + "  ").append(key).append(": ").append(dict1.get(key));
                    result.append("\n");
                } else {
                    result.append(INDENT + "- ").append(key).append(": ").append(dict1.get(key));
                    result.append("\n");
                    result.append(INDENT + "+ ").append(key).append(": ").append(dict2.get(key));
                    result.append("\n");
                }
            } else if (dict1.containsKey(key)) {
                result.append(INDENT + "- ").append(key).append(": ").append(dict1.get(key));
                result.append("\n");
            } else {
                result.append(INDENT + "+ ").append(key).append(": ").append(dict2.get(key));
                result.append("\n");
            }
        }
        System.out.println("{\n".concat(String.valueOf(result)).concat("}"));
    }

    public static Map<String, Object> getData(String readFilePath) throws Exception {
        Path path = Paths.get(readFilePath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        String json = Files.readString(path);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, new TypeReference<>(){});
    }
}
