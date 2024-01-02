package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputStream {
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
