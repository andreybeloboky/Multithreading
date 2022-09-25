package by.beloboky.calculateAllLetterFromFiles;

import java.io.*;
import java.util.*;

public class CalculateLettersService implements Runnable {
    private final LinkedList<String> fileLinkedList;
    private final HashMap<Character, Integer> lettersCount;

    public CalculateLettersService() {
        FileRepository fileRepository = new FileRepository();
        List<String> arr = fileRepository.findFiles();
        this.fileLinkedList = new LinkedList<>();
        this.fileLinkedList.addAll(arr);
        this.lettersCount = new HashMap<>();
        this.makeVowels();
    }

    public void makeVowels() {
        for (char i = 65; i <= 90; i++) {
            this.lettersCount.put(i, 0);
        }
        for (char j = 97; j <= 122; j++) {
            this.lettersCount.put(j, 0);
        }
    }

    /**
     * @param file - letters of this file must be calculated;
     */
    public void calculateLetters(String file) {
        File file1 = new File(file);
        try (FileInputStream readFile = new FileInputStream(file1);
             BufferedInputStream read = new BufferedInputStream(readFile)) {
            int number;
            while ((number = read.read()) != -1) {
                incrementIfExists(lettersCount, number);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param lettersCount - hashmap with the letters and amount(numbers)
     * @param number - byte from some file.
     */
    public void incrementIfExists(HashMap<Character, Integer> lettersCount, int number) {
        if (lettersCount.containsKey((char) number)) {
            lettersCount.replace((char) number, lettersCount.get((char) number) + 1);
        }
    }

    /**
     * @return - hashmap with the letters and amount(numbers)
     */
    public HashMap<Character, Integer> getLettersCount() {
        return lettersCount;
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
    public synchronized void run() {
        int i = 0;
        while (i < fileLinkedList.size()) {
            this.calculateLetters(fileLinkedList.pollFirst());
        }
    }
}
