package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;

public class Formatter {
    private static final Map<String, DiffFormatter> FORMATTERS = Map.of(
            "stylish", new StylishFormatter(),
            "plain", new PlainFormatter(),
            "json", new JsonFormatter());

    public static String format(
            Map<String, Map<String, Object>> diff,
            String formatName) {
        return FORMATTERS.get(formatName).format(diff);
    }
}
