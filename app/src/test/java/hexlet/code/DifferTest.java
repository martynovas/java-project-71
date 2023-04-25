package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DifferTest {
    @Test
    void testShouldBeSuccessful() throws Exception {
        var map1 = Map.of("key_1", "value_1", "key_2", "value_2", "key_3", "value_3");
        var map2 = Map.of("key_2", "value_2", "key_3", "value_33", "key_4", "value_4");
        var result = Differ.generate(map1, map2);
        assertThat(result)
                .isEqualTo("{\n"
                        + "  - key_1: value_1 \n"
                        + "  + key_2: value_2 \n"
                        + "  - key_3: value_3 \n"
                        + "  + key_3: value_33 \n"
                        + "  + key_4: value_4 \n"
                        + "}\n");
    }

    @Test
    void testWithEmptyMaps() throws Exception {
        var result = Differ.generate(Map.of(), Map.of());
        assertThat(result)
                .isEqualTo("{\n"
                        + "}\n");
    }

}
