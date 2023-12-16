package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {
    private String expectedStylish;
    private String expectedPlain;

    @BeforeEach
    public void setUp() {
        expectedStylish = Util.readFile("src/test/resources/expectedStylish.txt");
        expectedPlain = Util.readFile("src/test/resources/expectedPlain.txt");
    }

    @Test
    void testDifferStylishJson() {
        String file1 = "src/test/resources/fileJ1.json";
        String file2 = "src/test/resources/fileJ2.json";
        var actual = Differ.generate(file1, file2, "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    void testDifferStylishYaml() {
        String file1 = "src/test/resources/fileY1.yaml";
        String file2 = "src/test/resources/fileY2.yaml";
        var actual = Differ.generate(file1, file2, "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    void testDifferPlainJson() {
        String file1 = "src/test/resources/fileJ1.json";
        String file2 = "src/test/resources/fileJ2.json";
        var actual = Differ.generate(file1, file2, "plain");
        assertEquals(expectedPlain, actual);
    }

    @Test
    void testDifferPlainYaml() {
        String file1 = "src/test/resources/fileY1.yaml";
        String file2 = "src/test/resources/fileY2.yaml";
        var actual = Differ.generate(file1, file2, "plain");
        assertEquals(expectedPlain, actual);
    }
}
