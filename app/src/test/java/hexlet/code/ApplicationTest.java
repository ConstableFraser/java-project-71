package hexlet.code;

import hexlet.code.formatters.Json;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationTest {
    @ParameterizedTest
    @CsvSource({"src/test/resources/fileJ1.json,"
            + "src/test/resources/fileJ2.json,"
            + "src/test/resources/expectedStylish.txt,"
            + "stylish",
                "src/test/resources/fileY1.yaml,"
            + "src/test/resources/fileY2.yaml,"
            + "src/test/resources/expectedStylish.txt,"
            + "stylish",
                "src/test/resources/fileJ1.json,"
            + "src/test/resources/fileJ2.json,"
            + "src/test/resources/expectedPlain.txt,"
            + "plain",
                "src/test/resources/fileY1.yaml,"
            + "src/test/resources/fileY2.yaml,"
            + "src/test/resources/expectedPlain.txt,"
            + "plain",
                "src/test/resources/fileJ1.json,"
            + "src/test/resources/fileJ2.json,"
            + "src/test/resources/expectedJson.txt,"
            + "json",
                "src/test/resources/fileY1.yaml,"
            + "src/test/resources/fileY2.yaml,"
            + "src/test/resources/expectedJson.txt,"
            + "json"})
    void testCorrectGenerateDiffFormatsStylishPlainJson(ArgumentsAccessor argumentsAccessor) {
        String file1 = argumentsAccessor.getString(0);
        String file2 = argumentsAccessor.getString(1);
        String expected = Util.readFile(argumentsAccessor.getString(2));
        String format = argumentsAccessor.getString(3);

        var actual = Differ.generate(file1, file2, format);
        assertEquals(expected, actual);
    }

    @Test
    void testExceptionParseJson() {
        var map = new LinkedHashMap<String, LinkedList<Object>>();
        map.put(null, null);
        assertThrows(RuntimeException.class, () -> Json.format(map));
    }

    @Test
    void testGetDataMethod() {
        var wrongExtendedFile = "src/test/resources/wrongFileExtended.docx";
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
        var file1 = "src/test/resources/fileY1.yaml";
        var file2 = "src/test/resources/fileY2.yaml";
        var wrongFormatter = "noExistFormatter";
        assertThrows(Error.class, () -> Differ.generate(file1, file2, wrongFormatter));
    }

    @Test
    void testErrorsOfDictInDifferMethod() {
        var file1 = "src/test/resources/fileJ1.json";
        var file22 = "src/test/resources/emptyFile.json";
        var wrongFormatter = "noExistFormatter";
        assertThrows(Error.class, () -> Differ.generate(file1, file22, wrongFormatter));
    }
}
