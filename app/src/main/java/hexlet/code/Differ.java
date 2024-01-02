package hexlet.code;

import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2) {
        return Differ.generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String inputStream1, String inputStream2, String formatOutput) {
        Map<String, Object> dict1 = null;
        Map<String, Object> dict2 = null;

        var contentInput1 = InputStream.getContent(inputStream1);
        var formatInputStream1 = InputStream.getInputFormat(inputStream1);
        var contentInput2 = InputStream.getContent(inputStream2);
        var formatInputStream2 = InputStream.getInputFormat(inputStream2);

        try {
            dict1 = Parser.getData(contentInput1, formatInputStream1);
            dict2 = Parser.getData(contentInput2, formatInputStream2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dict1 == null || dict2 == null) {
            var fileError = dict1 == null ? inputStream1 : inputStream2;
            throw new Error("Error reading file: " + fileError);
        }

        var model = Calculator.run(dict1, dict2);
        return Formatter.print(model, formatOutput);
    }
}
