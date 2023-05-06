package hexlet.code.formatters;

import hexlet.code.DiffFormatter;

import java.util.Map;

public class StylishFormatter implements DiffFormatter {
    private static StringBuilder stringBuilder;

    private static void println(String sign, String key, Object value) {
        stringBuilder.append(String.format("  %s %s: %s\n", sign, key, value == null ? "null" : value.toString()));
    }

    public void formatElement(String key, Map<String, Object> diff) {
        switch ((String) diff.get("difference")) {
            case "added" -> println("+", key, diff.get("value"));
            case "equal" -> println(" ", key, diff.get("value"));
            case "updated" -> {
                println("-", key, diff.get("old_value"));
                println("+", key, diff.get("new_value"));
            }
            case "removed" -> println("-", key, diff.get("value"));
            default -> {
            }
        }
    }

    @Override
    public String format(Map<String, Map<String, Object>> diff) {
        stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");

        for (var e : diff.entrySet()) {
            formatElement(e.getKey(), e.getValue());
        }

        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }
}
