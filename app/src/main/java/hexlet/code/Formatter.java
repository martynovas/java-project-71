package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;

public class Formatter {
    private static final Map<String, DiffFormatter> FORMATTERS = Map.of(
            "stylish", new StylishFormatter(),
            "plain", new PlainFormatter());

    public static String format(
            Map<String, DiffType> diff,
            Map<String, JsonNode> map1,
            Map<String, JsonNode> map2,
            String formatName) {
        return FORMATTERS.get(formatName).format(diff, map1, map2);
    }
}
