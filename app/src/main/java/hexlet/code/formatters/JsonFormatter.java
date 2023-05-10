package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.DiffFormatter;

import java.util.Map;

public final class JsonFormatter implements DiffFormatter {

    @Override
    public String format(Map<String, Map<String, Object>> diff) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        return objectMapper.writeValueAsString(diff);
    }
}
