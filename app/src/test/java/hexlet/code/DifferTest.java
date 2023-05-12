package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public final class DifferTest {
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
    void defaultFormatTest() throws Exception {
        var result = Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json");

        assertThat(result)
                .isEqualTo(resultStylish);
    }

    @ParameterizedTest
    @MethodSource("testDataSource")
    void test(String filePath1, String filePath2, String formatName, String expectedResult) throws Exception {
        var result = Differ.generate(
                filePath1,
                filePath2,
                formatName);
        assertThat(result)
                .isEqualTo(expectedResult);
    }

    private static Stream<Arguments> testDataSource() {
        return Stream.of(
                Arguments.of("src/test/resources/file1.json", "src/test/resources/file2.json", "stylish",
                        resultStylish),
                Arguments.of("src/test/resources/file1.json", "src/test/resources/file2.json", "plain",
                        resultPlain),
                Arguments.of("src/test/resources/file1.json", "src/test/resources/file2.json", "json",
                        resultJson),
                Arguments.of("src/test/resources/file1.yml", "src/test/resources/file2.yml", "stylish",
                        resultStylish),
                Arguments.of("src/test/resources/file1.yml", "src/test/resources/file2.yml", "plain",
                        resultPlain),
                Arguments.of("src/test/resources/file1.yml", "src/test/resources/file2.yml", "json",
                        resultJson)
        );
    }
}
