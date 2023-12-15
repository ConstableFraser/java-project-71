package hexlet.code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Util {
    public static String readFile(String filePath) {
        StringBuilder result = new StringBuilder();

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
                if (bufferedReader.ready()) {
                    result.append(System.lineSeparator());
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
