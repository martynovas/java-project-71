package hexlet.code;

import java.util.Map;

public interface DiffFormatter {
    String format(Map<String, Map<String, Object>> diff);
}
