package hexlet.code;

import java.util.Map;
import java.util.stream.Stream;

public class Differ {
    private static Map<String, String> map1;
    private static Map<String, String> map2;

    private static StringBuilder stringBuilder;

    private static void println(String sign, String key, String value) {
        stringBuilder.append(String.format("  %s %s: %s \n", sign, key, value));
    }

    private static void compare(String key) {
        if (!map1.containsKey(key)) {
            println("+", key, map2.get(key));
        } else if (!map2.containsKey(key)) {
            println("-", key, map1.get(key));
        } else if (map1.get(key).equals(map2.get(key))) {
            println("+", key, map1.get(key));
        } else {
            println("-", key, map1.get(key));
            println("+", key, map2.get(key));
        }
    }

    public static String generate(Map<String, String> map1, Map<String, String> map2) throws Exception {
        Differ.map1 = map1;
        Differ.map2 = map2;

        stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        Stream.concat(
                        map1.keySet().stream(),
                        map2.keySet().stream()
                )
                .distinct()
                .sorted()
                .forEach(e -> compare((String) e));
        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }
}
