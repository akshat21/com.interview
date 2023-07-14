import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonExporter implements ResultExporter{

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String export(Map<String, Long> words) {
        try {
            return objectMapper.writeValueAsString(words);
        } catch (JsonProcessingException e) {
            System.out.printf("Some error occurred parsing the result to JSON format: %s%n", e.getMessage());
        }
        return "";
    }
}
