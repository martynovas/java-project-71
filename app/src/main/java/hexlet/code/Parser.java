package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String filepath) throws Exception {
        var file = Paths.get(filepath).toAbsolutePath().normalize().toFile();
        ObjectMapper objectMapper;

        if (file.toString().endsWith(".json")) {
            objectMapper = new ObjectMapper();
        } else if (file.toString().endsWith(".yml")) {
            objectMapper = new YAMLMapper();
        } else {
            throw new Exception("File type not supported");
        }

        Map map = objectMapper.readValue(file, Map.class);

        return map;
    }
}
