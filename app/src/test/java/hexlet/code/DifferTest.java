package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DifferTest {
    @Test
    void testShouldBeSuccessful() {
        assertThat("11")
                .isEqualTo("11");
    }

    @Test
    void testShouldBeFailed() {
        assertThat("33")
                .isEqualTo("33");
    }

}
