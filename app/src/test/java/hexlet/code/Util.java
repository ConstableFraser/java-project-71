package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Util {
    private static Path getFixturePath(String fileName) {
        return Paths.get(fileName).toAbsolutePath().normalize();
    }

    static String readFixture(String fileName) {
        Path filePath = getFixturePath(fileName);
        try {
            return Files.readString(filePath).trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
