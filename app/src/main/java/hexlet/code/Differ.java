package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Differ {
    private static String getFileExtension(String filePath) {
        return filePath.substring(filePath.lastIndexOf(".") + 1);
    }

    private static String readFile(String filePath) throws IOException {
        var file = Paths.get(filePath).toAbsolutePath().normalize();

        return Files.readString(file);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        var map1 = Parser.parse(
                getFileExtension(filePath1),
                readFile(filePath1));
        var map2 = Parser.parse(
                getFileExtension(filePath2),
                readFile(filePath2));

        var diff = DiffGenerator.generate(map1, map2);

        return Formatter.format(diff, formatName);
    }
}
