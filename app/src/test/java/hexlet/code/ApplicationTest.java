package hexlet.code;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ApplicationTest {
    private static final String ABSOLUTE_PATH = "src/test/resources/fixtures/";

    @ParameterizedTest
    @CsvSource({ABSOLUTE_PATH + "fileJ1.json,"
            + ABSOLUTE_PATH + "fileJ2.json,"
            + ABSOLUTE_PATH +  "expectedStylish.txt,"
            + "stylish",
                ABSOLUTE_PATH + "fileY1.yml,"
            + ABSOLUTE_PATH + "fileY2.yml,"
            + ABSOLUTE_PATH + "expectedStylish.txt,"
            + "stylish",
                ABSOLUTE_PATH + "fileJ1.json,"
            + ABSOLUTE_PATH + "fileJ2.json,"
            + ABSOLUTE_PATH + "expectedPlain.txt,"
            + "plain",
                ABSOLUTE_PATH + "fileY1.yml,"
            + ABSOLUTE_PATH + "fileY2.yml,"
            + ABSOLUTE_PATH + "expectedPlain.txt,"
            + "plain"})
    void testCorrectDifferFormatsStylishPlain(String filePath1, String filePath2, String expectedFile, String format) {
        String expected = Util.readFixture(expectedFile);
        String actual;
        try {
            actual = Differ.generate(filePath1, filePath2, format);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(expected, actual);
    }

    @Test
    void testDifferWithoutFormat() {
        String expected = Util.readFixture(ABSOLUTE_PATH + "expectedStylish.txt");

        String filePath1 = ABSOLUTE_PATH + "fileJ1.json";
        String filePath2 = ABSOLUTE_PATH + "fileJ2.json";
        String actual;
        try {
            actual = Differ.generate(filePath1, filePath2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(expected, actual);
    }

    @Test
    void testDifferFormatJson() {
        String expected = Util.readFixture(ABSOLUTE_PATH + "expectedJson.txt");

        String filePath1 = ABSOLUTE_PATH + "fileY1.yml";
        String filePath2 = ABSOLUTE_PATH + "fileY2.yml";
        String actual;
        try {
            actual = Differ.generate(filePath1, filePath2, "json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testWrongFormatter() {
        var file1 = ABSOLUTE_PATH + "fileY1.yml";
        var file2 = ABSOLUTE_PATH + "fileY2.yml";
        var wrongFormatter = "noExistFormatter";
        assertThrows(Error.class, () -> Differ.generate(file1, file2, wrongFormatter));
    }
}
