package hexlet.code.formatters;

import com.fasterxml.jackson.databind.JsonNode;
import hexlet.code.DiffFormatter;
import hexlet.code.DiffType;

import java.util.Map;

public class PlainFormatter implements DiffFormatter {
    private static StringBuilder stringBuilder;

    private String getStringValue(JsonNode value) {
        return switch (value.getNodeType()) {
            case OBJECT, ARRAY -> "[complex value]";
            case STRING -> "\'" + value.asText() + "\'";
            default -> value.asText();
        };
    }

    public String format(Map<String, DiffType> diff, Map<String, JsonNode> map1, Map<String, JsonNode> map2) {
        stringBuilder = new StringBuilder();

        for (var e : diff.entrySet()) {
            var key = e.getKey();
            var diffType = e.getValue();
            var value1 = map1.get(key);
            var value2 = map2.get(key);

            switch (diffType) {
                case ADDED -> stringBuilder.append(
                        String.format("Property '%s' was added with value: %s\n",
                                key,
                                getStringValue(value2)));
                case UPDATED -> stringBuilder.append(
                        String.format("Property '%s' was updated. From %s to %s\n",
                                key,
                                getStringValue(value1),
                                getStringValue(value2)));
                case REMOVED -> stringBuilder.append(String.format("Property '%s' was removed\n", key));
                default -> {
                }
            }
        }

        return stringBuilder.toString();
    }
}
