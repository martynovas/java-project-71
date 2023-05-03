package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, JsonNode> parse(String filepath) throws Exception {
        var file = Paths.get(filepath).toAbsolutePath().normalize().toFile();
        ObjectMapper objectMapper;

        if (file.toString().endsWith(".json")) {
            objectMapper = new ObjectMapper();
        } else if (file.toString().endsWith(".yml")) {
            objectMapper = new YAMLMapper();
        } else {
            throw new Exception("File type not supported");
        }

        Map<String, JsonNode> map = new HashMap<>();

        var fields = objectMapper.readTree(file).fields();

        while (fields.hasNext()) {
            var field = fields.next();
            map.put(field.getKey(), field.getValue());
        }

        return map;
    }
}
