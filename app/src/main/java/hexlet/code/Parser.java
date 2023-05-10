package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String format, String content) throws Exception {
        var objectMapper = switch (format) {
            case "json" -> new ObjectMapper();
            case "yml", "yaml" -> new YAMLMapper();
            default -> throw new Exception("Unknown format: " + format);
        };

        Map map = objectMapper.readValue(content, Map.class);

        return map;
    }
}
