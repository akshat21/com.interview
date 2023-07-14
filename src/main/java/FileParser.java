import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileParser {

    public static List<String> parseWords(String filePath, String wordSeparator) {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            return lines.stream()
                    .map(line -> line.split(wordSeparator))
                    .flatMap(strings -> Arrays.stream(strings).map(String::trim))
                    .collect(Collectors.toList());
        }
        catch (IOException e){
            System.out.printf("Error occurred reading the file: %s", e.getMessage());
        }
        return List.of();
    }
}
