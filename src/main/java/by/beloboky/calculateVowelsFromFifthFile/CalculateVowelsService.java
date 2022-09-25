package by.beloboky.calculateVowelsFromFifthFile;

import java.util.LinkedList;
import java.util.List;

public class CalculateVowelsService implements Runnable {

    private final List<String> filePath;
    private final List<Character> vowelsArray;
    private int count = 0;

    /**
     * @param str - file path;
     */
    public CalculateVowelsService(String str) {
        FilesRepository readFromFile = new FilesRepository(str);
        this.filePath = readFromFile.readFromFile();
        this.vowelsArray = new LinkedList<>();
        this.makeVowels(this.vowelsArray);
    }

    /**
     * @param vowels - vowel letters;
     */
    public void makeVowels(List<Character> vowels) {
        vowels.add('a');
        vowels.add('A');
        vowels.add('e');
        vowels.add('E');
        vowels.add('I');
        vowels.add('i');
        vowels.add('O');
        vowels.add('o');
        vowels.add('U');
        vowels.add('u');
        vowels.add('Y');
        vowels.add('y');
    }

    /**
     * @return - amount of the vowel letters;
     */
    public int getCount() {
        return count;
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
        for (String s : filePath) {
            char[] ch = s.toCharArray();
            for (char value : ch) {
                for (char vowels : vowelsArray) {
                    if (value == vowels) {
                        count++;
                    }
                }
            }
        }
    }
}
