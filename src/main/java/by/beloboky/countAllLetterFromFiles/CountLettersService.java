package by.beloboky.countAllLetterFromFiles;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CountLettersService implements Runnable {
    private final LinkedList<String> files;
    private Map<Character, Integer> lettersCount;

    public CountLettersService() {
        this.files = new LinkedList<>();
        this.initFiles();
        this.initLetters();
    }

    private void initFiles() {
        FileRepository fileRepository = new FileRepository();
        List<String> arr = fileRepository.findFiles();
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
     * @param filePaths - letters of this filePaths must be calculated;
     */
    public void countLetters(String filePaths) {
        File file = new File(filePaths);
        try (Stream<String> streamFromFiles = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
            List<String> str = streamFromFiles.toList();
            incrementIfExists(str);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * @param str - byte from some file.
     */
    public void incrementIfExists(List<String> str) {
        for (String v : str) {
            char[] charString = v.toCharArray();
            for (char value : charString) {
                lettersCount.computeIfPresent(value, (a, b) -> lettersCount.get(value) + 1);
            }
        }
    }

    /**
     * @return - hashmap with the letters and amount(numbers)
     */
    public Map<Character, Integer> getLettersCount() {
        return lettersCount;
    }

    private synchronized String init() {
        return files.pollFirst();
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
        long nanoTime = System.nanoTime();
        int i = 0;
        int j = 0;
        while (i < files.size()) {
            this.countLetters(this.init());
            j++;
        }
        System.out.println();
        System.out.printf("Thread %s process all files by %s. Files - %s",
                Thread.currentThread().getId(),
                System.nanoTime() - nanoTime, j);
    }
}
