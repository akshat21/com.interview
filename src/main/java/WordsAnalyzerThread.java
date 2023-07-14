import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WordsAnalyzerThread extends Thread{

    private final Map<String, Long> wordCount;
    private final WordsAnalyzer wordsAnalyzer;

    public WordsAnalyzerThread(List<String> words, ConcurrentHashMap<String, Long> wordCount){
        this.wordCount = wordCount;
        this.wordsAnalyzer = new WordsAnalyzer(words);
    }

    @Override
    public void run() {
        this.wordsAnalyzer.calculateSimilarWordCount(wordCount);
    }
}
