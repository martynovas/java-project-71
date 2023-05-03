package hexlet.code;

import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        var map1 = Parser.parse(filePath1);
        var map2 = Parser.parse(filePath2);

        var diff = Stream.concat(
                        map1.keySet().stream(),
                        map2.keySet().stream()
                )
                .distinct()
                .collect(Collectors.toMap(
                        key -> key,
                        key -> !map1.containsKey(key) ? DiffType.ADDED
                                : !map2.containsKey(key) ? DiffType.REMOVED
                                : map1.get(key).equals(map2.get(key)) ? DiffType.EQUAL
                                : DiffType.UPDATED,
                        (a, b) -> a,
                        TreeMap::new));

        return Formatter.format(diff, map1, map2, formatName);
    }
}
