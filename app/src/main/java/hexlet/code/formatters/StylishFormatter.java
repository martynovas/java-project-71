package hexlet.code.formatters;

import hexlet.code.DiffFormatter;

import java.util.Map;

public final class StylishFormatter implements DiffFormatter {
    private static String formatLine(String sign, String key, Object value) {
        return String.format("  %s %s: %s\n", sign, key, value == null ? "null" : value.toString());
    }

    public String formatElement(String key, Map<String, Object> diff) throws Exception {
        return switch ((String) diff.get("difference")) {
            case "added" -> formatLine("+", key, diff.get("value"));
            case "equal" -> formatLine(" ", key, diff.get("value"));
            case "updated" -> formatLine("-", key, diff.get("old_value"))
                    + formatLine("+", key, diff.get("new_value"));
            case "removed" -> formatLine("-", key, diff.get("value"));
            default -> throw new Exception("Unknown difference: " + diff.get("difference"));
        };
    }

    @Override
    public String format(Map<String, Map<String, Object>> diff) throws Exception {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");

        for (var e : diff.entrySet()) {
            stringBuilder.append(formatElement(e.getKey(), e.getValue()));
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
