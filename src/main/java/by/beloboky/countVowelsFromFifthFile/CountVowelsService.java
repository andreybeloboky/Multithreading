package by.beloboky.countVowelsFromFifthFile;

import by.beloboky.countAllLetterFromFiles.FileRepository;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class CountVowelsService implements Runnable {

    private final LinkedList<String> files;
    private final List<Character> vowelsArray;
    private volatile int count = 0;

    public CountVowelsService() {
        this.files = new LinkedList<>();
        this.vowelsArray = new LinkedList<>();
        this.initFiles();
        this.initVowelsArray();
    }

    private void initFiles() {
        FilesRepository readFromFile = new FilesRepository();
        List<String> arr = readFromFile.readFromFile();
        this.files.addAll(arr);
    }

    public void initVowelsArray() {
        this.vowelsArray.add('a');
        this.vowelsArray.add('A');
        this.vowelsArray.add('e');
        this.vowelsArray.add('E');
        this.vowelsArray.add('I');
        this.vowelsArray.add('i');
        this.vowelsArray.add('O');
        this.vowelsArray.add('o');
        this.vowelsArray.add('U');
        this.vowelsArray.add('u');
        this.vowelsArray.add('Y');
        this.vowelsArray.add('y');
    }

    /**
     * @return - amount of the vowel letters;
     */
    public int getCount() {
        return count;
    }

    /**
     * @return - string from list if it isn't null;
     */
    private synchronized String init() {
        String s = files.pollFirst();
        return Objects.requireNonNullElseGet(s, () -> files.pollFirst() + 1);
    }

    /**
     * @param s - path to file.
     */
    private void readFile(String s) {
        File file = new File(s);
        try (Stream<String> streamFromFiles = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
            List<String> str = streamFromFiles.toList();
            countVowels(str);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * @param list - text from File.
     */
    private synchronized void countVowels(List<String> list) {
        for (String value : list) {
            char[] fromStringToChar = value.toCharArray();
            for (char stringAsChar : fromStringToChar) {
                for (char vowels : this.vowelsArray) {
                    if (stringAsChar == vowels) {
                        this.count++;
                    }
                }
            }
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
        int i = 0;
        while (i < files.size()) {
            this.readFile(this.init());
        }
    }
}
