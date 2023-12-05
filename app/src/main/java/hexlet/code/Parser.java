package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> getData(String readFilePath) throws Exception {
        Path path = Paths.get(readFilePath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        String data = Files.readString(path);

        if (path.toString().endsWith(".json")) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(data, new TypeReference<>() { });
        } else if (path.toString().endsWith(".yaml")) {
            ObjectMapper mapper = new YAMLMapper();
            return mapper.readValue(data, new TypeReference<>() { });
        }
        return null;
    }
}
