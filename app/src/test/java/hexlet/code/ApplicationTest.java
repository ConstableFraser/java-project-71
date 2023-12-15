package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {
    private String expected;

    @BeforeEach
    public void setUp() {
        expected = Util.readFile("src/test/resources/result.txt");
    }

    @Test
    void testDifferGenerateJSON() {
        String file1 = "src/test/resources/fileJ1.json";
        String file2 = "src/test/resources/fileJ2.json";
        var model = Differ.generate(file1, file2);
        var actual = Formatter.print(model, "stylish");
        assertEquals(expected, actual);
    }

    @Test
    void testDifferGenerateYAML() {
        String file1 = "src/test/resources/fileY1.yaml";
        String file2 = "src/test/resources/fileY2.yaml";
        var model = Differ.generate(file1, file2);
        var actual = Formatter.print(model, "stylish");
        assertEquals(expected, actual);
    }
}
