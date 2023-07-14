import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FileAnalyzer {

    private static final String FILE = "src/main/resources/TestFile.txt";
    private static final int NUM_THREADS = 4;

    public static void main(String[] args) throws InterruptedException {

        //Parse the File
        List<String> words = FileParser.parseWords(FILE, "\\s+");

        // Initialize threads
        WordsAnalyzerThread [] threads = new WordsAnalyzerThread[NUM_THREADS];
        List<List<String>> subList = partitionList(words, NUM_THREADS);

        //start the threads execution
        ConcurrentHashMap<String, Long> wordCount = new ConcurrentHashMap<>();
        for(int i =0 ; i< NUM_THREADS; i++) {
            threads[i] = new WordsAnalyzerThread(subList.get(i), wordCount);
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i].join();
        }

        //Export the Result
        final ResultExporter exporter = new JsonExporter();
        String result = exporter.export(wordCount);
        System.out.println(result);
    }

    public static List<List<String>> partitionList(List<String> words, int parts){
        int inputSize = words.size();
        int partitionSize = (int) Math.ceil((double) inputSize / parts);
        return  IntStream.range(0, parts)
                .mapToObj(i -> words.subList(i * partitionSize, Math.min((i + 1) * partitionSize, inputSize)))
                .collect(Collectors.toList());
    }
}
