package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {
    private static Map<String, Object> map1;
    private static Map<String, Object> map2;


    private static Map<String, Object> genereateElement(String key) {
        var map = new LinkedHashMap<String, Object>();
        if (!map1.containsKey(key)) {
            map.put("difference", "added");
            map.put("value", map2.get(key));
        } else if (!map2.containsKey(key)) {
            map.put("difference", "removed");
            map.put("value", map1.get(key));
        } else if (Objects.equals(map1.get(key), map2.get(key))) {
            map.put("difference", "equal");
            map.put("value", map1.get(key));
        } else {
            map.put("difference", "updated");
            map.put("old_value", map1.get(key));
            map.put("new_value", map2.get(key));
        }

        return map;
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        map1 = Parser.parse(filePath1);
        map2 = Parser.parse(filePath2);

        var diff = Stream.concat(
                        map1.keySet().stream(),
                        map2.keySet().stream()
                )
                .distinct()
                .collect(Collectors.toMap(
                        key -> key,
                        key -> genereateElement(key),
                        (a, b) -> a,
                        TreeMap::new));

        return Formatter.format(diff, formatName);
    }
}
