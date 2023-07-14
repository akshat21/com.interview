import java.util.List;
import java.util.Map;

public class WordsAnalyzer {

    private final List<String> words ;

    public WordsAnalyzer(final List<String> words){
        this.words = words;
    }

    public void calculateSimilarWordCount(Map<String, Long> result){
        if(this.words != null ){
            this.words
                    .stream()
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .filter(word -> word.length() > 1)
                    .forEach( word -> result.put(word, result.getOrDefault(word,0L) + 1));
        }

    }
}
