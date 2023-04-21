package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", paramLabel = "format")
    private String format = "stylish";

    private Map<String, String> map1;

    private Map<String, String> map2;

    private void println(String sign, String key, String value) {
        System.out.println(String.format("  %s %s: %s", sign, key, value));
    }

    private void compare(String key) {
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

    @Override
    public Integer call() throws Exception {
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        ObjectMapper objectMapper = new ObjectMapper();
        map1 = objectMapper.readValue(path1.toFile(), new TypeReference<Map<String, String>>() {
        });
        map2 = objectMapper.readValue(path2.toFile(), new TypeReference<Map<String, String>>() {
        });

        var diff = Differ.generate(map1, map2);

        System.out.println(diff);

        return null;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
