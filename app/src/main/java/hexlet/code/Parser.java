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

        var filePath = path.toString();
        var fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1);
        ObjectMapper mapper = switch (fileExtension) {
            case "json" -> new ObjectMapper();
            case "yml" -> new YAMLMapper();
            default -> throw new Error("Unknown file extension: " + fileExtension);
        };
        return mapper.readValue(data, new TypeReference<>() { });
    }
}
