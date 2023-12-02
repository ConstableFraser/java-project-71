package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {
    private String expected;

    @BeforeEach
    public void setUp() {
        expected = readFile("src/test/resources/result.txt");
    }

    @Test
    @DisplayName("'Diff.generate' method works correctly!")
    void testDifferGenerate() {
        String file1 = "src/test/resources/file1.json";
        String file2 = "src/test/resources/file2.json";
        assertEquals(expected, Differ.generate("stylish", file1, file2));
    }

    public String readFile(String filePath) {
        StringBuilder result = new StringBuilder();

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
                result.append("\\n");
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
