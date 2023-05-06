package hexlet.code.formatters;

import hexlet.code.DiffFormatter;

import java.util.Collection;
import java.util.Map;

public class PlainFormatter implements DiffFormatter {
    private static StringBuilder stringBuilder;

    private String getStringValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof Collection<?> || value instanceof Map) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }

    public void formatElement(String key, Map<String, Object> diff) {
        switch ((String) diff.get("difference")) {
            case "added" -> stringBuilder.append(
                    String.format("Property '%s' was added with value: %s\n",
                            key,
                            getStringValue(diff.get("value"))));
            case "updated" -> stringBuilder.append(
                    String.format("Property '%s' was updated. From %s to %s\n",
                            key,
                            getStringValue(diff.get("old_value")),
                            getStringValue(diff.get("new_value"))));
            case "removed" -> stringBuilder.append(String.format("Property '%s' was removed\n", key));
            default -> {
            }
        }
    }

    @Override
    public String format(Map<String, Map<String, Object>> diff) {
        stringBuilder = new StringBuilder();

        for (var e : diff.entrySet()) {
            formatElement(e.getKey(), e.getValue());
        }

        return stringBuilder.toString();
    }
}
