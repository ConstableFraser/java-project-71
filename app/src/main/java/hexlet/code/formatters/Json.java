package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Json {
    public static String format(Map<String, HashMap<String, Object>> modelData) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(modelData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
