package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiffGenerator {
    private static Map<String, Object> generateElement(
            String key,
            Map<String, Object> oldMap,
            Map<String, Object> newMap) {
        var map = new LinkedHashMap();
        if (!oldMap.containsKey(key)) {
            map.put("difference", "added");
            map.put("value", newMap.get(key));
        } else if (!newMap.containsKey(key)) {
            map.put("difference", "removed");
            map.put("value", oldMap.get(key));
        } else if (Objects.equals(oldMap.get(key), newMap.get(key))) {
            map.put("difference", "equal");
            map.put("value", oldMap.get(key));
        } else if (!Objects.equals(oldMap.get(key), newMap.get(key))) {
            map.put("difference", "updated");
            map.put("old_value", oldMap.get(key));
            map.put("new_value", newMap.get(key));
        }

        return map;
    }

    public static Map<String, Map<String, Object>> generate(Map<String, Object> oldMap, Map<String, Object> newMap) {
        return Stream.concat(
                        oldMap.keySet().stream(),
                        newMap.keySet().stream()
                )
                .distinct()
                .collect(Collectors.toMap(
                        key -> key,
                        key -> generateElement(key, oldMap, newMap),
                        (a, b) -> a,
                        TreeMap::new));
    }
}
