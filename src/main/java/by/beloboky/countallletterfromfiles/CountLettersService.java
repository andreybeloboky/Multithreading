package by.beloboky.countallletterfromfiles;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CountLettersService implements Runnable {
    private final LinkedList<Path> files;
    private Map<Character, Integer> lettersCount;

    public CountLettersService() {
        this.files = new LinkedList<>();
        this.initFiles();
        this.initLetters();
    }

    /**
     * @return - hashmap with the letters and amount(numbers)
     */
    public Map<Character, Integer> getLettersCount() {
        return lettersCount;
    }

    private void initFiles() {
        FileRepository fileRepository = new FileRepository();
        List<Path> arr = fileRepository.findFiles();
        this.files.addAll(arr);
    }

    private void initLetters() {
        this.lettersCount = new HashMap<>();
        for (char i = 65; i <= 90; i++) {
            this.lettersCount.put(i, 0);
        }
        for (char j = 97; j <= 122; j++) {
            this.lettersCount.put(j, 0);
        }
    }

    /**
     * @param filePath - letters of this filePaths must be calculated;
     */
    private void countLetters(Path filePath) {
        Path file = Paths.get(String.valueOf(filePath));
        try (Stream<String> streamFromFiles = Files.lines(file, StandardCharsets.UTF_8)) {
            List<String> str = streamFromFiles.toList();
            incrementIfExists(str);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * @param str - text (strings in array) from File.
     */
    private void incrementIfExists(List<String> str) {
        for (String v : str) {
            char[] charString = v.toCharArray();
            for (char value : charString) {
                lettersCount.computeIfPresent(value, (a, b) -> lettersCount.get(value) + 1);
            }
        }
    }

    /**
     * @return take first element from List and delete/remove its immediately.
     */
    private synchronized Path poll() {
        return files.pollFirst();
    }

    private synchronized void takeElementFromList(){
        int i = 0;
        while (i < files.size()) {
            this.countLetters(this.poll());
        }
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        this.takeElementFromList();
    }
}
