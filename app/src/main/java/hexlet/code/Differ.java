package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return Differ.generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String inputStream1,
                                  String inputStream2,
                                  String formatOutput) throws Exception {
        var contentInput1 = getContent(inputStream1);
        var formatInputStream1 = getInputFormat(inputStream1);
        var contentInput2 = getContent(inputStream2);
        var formatInputStream2 = getInputFormat(inputStream2);

        Map<String, Object> dict1 = Parser.getData(contentInput1, formatInputStream1);
        Map<String, Object> dict2 = Parser.getData(contentInput2, formatInputStream2);

        var model = TreeMaker.run(dict1, dict2);
        return Formatter.format(model, formatOutput);
    }

    public static String getContent(String inputStream) {
        Path path = Paths.get(inputStream).toAbsolutePath().normalize();

        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getInputFormat(String inputStream) {
        String path = Paths.get(inputStream).toAbsolutePath().normalize().toString();
        return path.substring(path.lastIndexOf(".") + 1);
    }
}
