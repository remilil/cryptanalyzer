import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriter {

    public static void writeFile(String content, String filePath) throws IOException {
        Path path = Path.of(filePath);
        Files.writeString(path, content);
        System.out.println("✅ Файл успешно сохранён: " + filePath);
    }
}