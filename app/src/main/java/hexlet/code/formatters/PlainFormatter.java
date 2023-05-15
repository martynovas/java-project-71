package hexlet.code.formatters;

import hexlet.code.DiffFormatter;

import java.util.Collection;
import java.util.Map;

public final class PlainFormatter implements DiffFormatter {
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

    private String formatElement(String key, Map<String, Object> diff) throws Exception {
        return switch ((String) diff.get("difference")) {
            case "added" -> String.format("Property '%s' was added with value: %s\n",
                            key,
                            getStringValue(diff.get("value")));
            case "equal" -> "";
            case "updated" -> String.format("Property '%s' was updated. From %s to %s\n",
                            key,
                            getStringValue(diff.get("old_value")),
                            getStringValue(diff.get("new_value")));
            case "removed" -> String.format("Property '%s' was removed\n", key);
            default -> throw new Exception("Unknown difference: " + diff.get("difference"));
        };
    }

    @Override
    public String format(Map<String, Map<String, Object>> diff) throws Exception {
        var stringBuilder = new StringBuilder();

        for (var e : diff.entrySet()) {
            stringBuilder.append(formatElement(e.getKey(), e.getValue()));
        }

        return stringBuilder.toString().trim();
    }
}
