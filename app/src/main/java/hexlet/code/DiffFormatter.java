package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public interface DiffFormatter {
    String format(Map<String, DiffType> diff, Map<String, JsonNode> map1, Map<String, JsonNode> map2);
}
