import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FileParserTest {

    @Test
    public void test_shouldParseTextFile() {
        List<String> words = FileParser.parseWords("src/test/resources/test.txt", "\\s+");
        assertTrue(words.containsAll(List.of("abc","Def","GHI")));
    }

    @Test
    public void test_shouldParseCsvFile() {
        List<String> words = FileParser.parseWords("src/test/resources/test.csv", ",");
        assertTrue(words.containsAll(List.of("abc","def","ghi")));
    }

    @Test
    public void test_whenFilePathIsInvalid(){
        List<String> words = FileParser.parseWords("src/test/resources/invalid.csv", ",");
        assertTrue(words.isEmpty());
    }

    @Test
    public void test_whenFileSeparatorIsNull(){
        List<String> words = FileParser.parseWords("src/test/resources/invalid.csv", null);
        assertTrue(words.isEmpty());
    }
}