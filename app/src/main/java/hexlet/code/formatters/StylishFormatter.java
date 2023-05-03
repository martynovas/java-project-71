package hexlet.code.formatters;

import com.fasterxml.jackson.databind.JsonNode;
import hexlet.code.DiffFormatter;
import hexlet.code.DiffType;

import java.util.Map;

public class StylishFormatter implements DiffFormatter {
    private static StringBuilder stringBuilder;

    private static void println(String sign, String key, JsonNode value) {
        switch (value.getNodeType()) {
            case OBJECT, ARRAY ->
                    stringBuilder.append(String.format("  %s %s: %s\n", sign, key, value.toString().replace("\"", "")));
            default -> stringBuilder.append(String.format("  %s %s: %s\n", sign, key, value.asText()));
        }
    }

    public String format(Map<String, DiffType> diff, Map<String, JsonNode> map1, Map<String, JsonNode> map2) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");

        for (var e : diff.entrySet()) {
            switch (e.getValue()) {
                case ADDED -> println("+", e.getKey(), map2.get(e.getKey()));
                case EQUAL -> println(" ", e.getKey(), map2.get(e.getKey()));
                case UPDATED -> {
                    println("-", e.getKey(), map1.get(e.getKey()));
                    println("+", e.getKey(), map2.get(e.getKey()));
                }
                case REMOVED -> println("-", e.getKey(), map1.get(e.getKey()));
                default -> { }
            }
        }

        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }
}
