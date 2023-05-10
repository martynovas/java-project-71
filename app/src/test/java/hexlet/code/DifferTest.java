package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    private static String resultJson;
    private static String resultPlain;
    private static String resultStylish;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultJson = readFixture("result_json.json");
        resultPlain = readFixture("result_plain.txt");
        resultStylish = readFixture("result_stylish.txt");
    }

    @Test
    void jsonDefaultTest() throws Exception {
        var result = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json");

        assertThat(result)
                .isEqualTo(resultStylish);
    }

    @Test
    void jsonStylishTest() throws Exception {
        var result = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "stylish");

        assertThat(result)
                .isEqualTo(resultStylish);
    }

    @Test
    void jsonPlainTest() throws Exception {
        var result = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "plain");

        assertThat(result)
                .isEqualTo(resultPlain);
    }

    @Test
    void jsonJsonTest() throws Exception {
        var result = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "json");
        assertThat(result)
                .isEqualTo(resultJson);
    }

    @Test
    void ymlDefaultTest() throws Exception {
        var result = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json");

        assertThat(result)
                .isEqualTo(resultStylish);
    }

    @Test
    void ymlStylishTest() throws Exception {
        var result = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "stylish");

        assertThat(result)
                .isEqualTo(resultStylish);
    }

    @Test
    void ymlPlainTest() throws Exception {
        var result = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "plain");

        assertThat(result)
                .isEqualTo(resultPlain);
    }

    @Test
    void ymlJsonTest() throws Exception {
        var result = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "json");
        assertThat(result)
                .isEqualTo(resultJson);
    }
}
