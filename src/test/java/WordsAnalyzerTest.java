import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordsAnalyzerTest {


    WordsAnalyzer subjectToTest;
    List<String> words = List.of("Hello", "how", "are", "You","you");

    @BeforeEach
    public void setup(){
        this.subjectToTest = new WordsAnalyzer(words);
    }

    @Test
    public void test_shouldCountSimilarWords(){
        Map<String, Long> count = new HashMap<>();
        subjectToTest.calculateSimilarWordCount(count);
        assertEquals(1L, (long) count.get("hello"));
        assertEquals(1L, (long) count.get("how"));
        assertEquals(1L, (long) count.get("are"));
    }

    @Test
    public void test_wordsShouldBeCaseInsensitive(){
        Map<String, Long> count = new HashMap<>();
        subjectToTest.calculateSimilarWordCount(count);
        assertEquals(2L, (long) count.get("you"));
    }

    @Test
    public void test_WhenEmptyWordsList_shouldNotThrowException(){
        Map<String, Long> count = new HashMap<>();
        subjectToTest = new WordsAnalyzer(List.of());
        subjectToTest.calculateSimilarWordCount(count);
        assertTrue(count.keySet().isEmpty());
    }

    @Test
    public void test_whenWordsHaveLeadingSpaces_shouldTrim(){
        Map<String, Long> count = new HashMap<>();
        subjectToTest = new WordsAnalyzer(List.of("  Hello","hello"));
        subjectToTest.calculateSimilarWordCount(count);
        assertEquals(2L, (long) count.get("hello"));
    }

    @Test
    public void testSimilarWordsCount_whenWordsHaveTrailingSpaces_shouldTrim(){
        Map<String, Long> count = new HashMap<>();
        subjectToTest = new WordsAnalyzer(List.of("Hello  ","hello"));
        subjectToTest.calculateSimilarWordCount(count);
        assertEquals(2L, (long) count.get("hello"));
    }

    @Test
    public void testSimilarWordsCount_resultMapPassedIsNull_shouldNotThrowException(){
        Map<String, Long> count = new HashMap<>();
        subjectToTest = new WordsAnalyzer(null);
        subjectToTest.calculateSimilarWordCount(count);
    }
}