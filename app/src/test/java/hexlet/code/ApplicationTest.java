package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {
    private String expected;

    @BeforeEach
    public void setUp() {
        expected = Util.readFile("src/test/resources/result.txt");
    }

    @Test
    @DisplayName("'Diff.generate' method works correctly with JSON!")
    void testDifferGenerateJSON() {
        String file1 = "src/test/resources/file1.json";
        String file2 = "src/test/resources/file2.json";
        assertEquals(expected, Differ.generate("stylish", file1, file2));
    }

    @Test
    @DisplayName("'Diff.generate' method works correctly with YAML!")
    void testDifferGenerateYAML() {
        String file1 = "src/test/resources/file1.yaml";
        String file2 = "src/test/resources/file2.yaml";
        assertEquals(expected, Differ.generate("stylish", file1, file2));
    }
}
