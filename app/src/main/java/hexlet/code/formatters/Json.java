package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedList;
import java.util.Map;

public class Json {
    public static String format(Map<String, LinkedList<Object>> modelData) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(modelData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
