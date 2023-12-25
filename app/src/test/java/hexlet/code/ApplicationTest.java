package hexlet.code;

import hexlet.code.formatters.Json;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class ApplicationTest {
    @ParameterizedTest
    @CsvSource({"src/test/resources/fixtures/fileJ1.json,"
            + "src/test/resources/fixtures/fileJ2.json,"
            + "src/test/resources/fixtures/expectedStylish.txt,"
            + "stylish",
                "src/test/resources/fixtures/fileY1.yml,"
            + "src/test/resources/fixtures/fileY2.yml,"
            + "src/test/resources/fixtures/expectedStylish.txt,"
            + "stylish",
                "src/test/resources/fixtures/fileJ1.json,"
            + "src/test/resources/fixtures/fileJ2.json,"
            + "src/test/resources/fixtures/expectedPlain.txt,"
            + "plain",
                "src/test/resources/fixtures/fileY1.yml,"
            + "src/test/resources/fixtures/fileY2.yml,"
            + "src/test/resources/fixtures/expectedPlain.txt,"
            + "plain",
                "src/test/resources/fixtures/fileY1.yml,"
            + "src/test/resources/fixtures/fileY2.yml,"
            + "src/test/resources/fixtures/expectedStylish.txt,"})
    void testCorrectDifferFormatsStylishPlain(String filePath1, String filePath2, String expectedFile, String format) {
        String expected = Util.readFixture(expectedFile);
        var actual = Differ.generate(filePath1, filePath2, format);
        assertEquals(expected, actual);
    }

    @Test
    void testCorrectDifferFormatJson() {
        String expected = Util.readFixture("src/test/resources/fixtures/expectedJson.txt");

        String filePath1 = "src/test/resources/fixtures/fileY1.yml";
        String filePath2 = "src/test/resources/fixtures/fileY2.yml";
        String actual = Differ.generate(filePath1, filePath2, "json");
        try {
            JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testExceptionParseJson() {
        var map = new LinkedHashMap<String, LinkedList<Object>>();
        map.put(null, null);
        assertThrows(RuntimeException.class, () -> Json.format(map));
    }

    @Test
    void testGetDataMethod() {
        var wrongExtendedFile = "src/test/resources/fixtures/wrongFileExtended.docx";
        var wrongFilePath = "nonexist/filePath.zzz";
        try {
            assertThrows(Error.class, () -> Parser.getData(wrongExtendedFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThrows(Exception.class, () -> Parser.getData(wrongFilePath));
    }

    @Test
    void testWrongFormatter() {
        var file1 = "src/test/resources/fixtures/fileY1.yml";
        var file2 = "src/test/resources/fixtures/fileY2.yml";
        var wrongFormatter = "noExistFormatter";
        assertThrows(Error.class, () -> Differ.generate(file1, file2, wrongFormatter));
    }
}
