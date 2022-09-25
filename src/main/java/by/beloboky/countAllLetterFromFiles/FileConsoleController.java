package by.beloboky.countAllLetterFromFiles;

import java.util.Map;

public class FileConsoleController {

    public static void main(String[] args) {
        CountLettersService findAmountOfLetters = new CountLettersService();
        Thread t1 = new Thread(findAmountOfLetters);
        t1.start();
        Thread t2 = new Thread(findAmountOfLetters);
        t2.start();
        Thread t3 = new Thread(findAmountOfLetters);
        t3.start();
        Thread t4 = new Thread(findAmountOfLetters);
        t4.start();
        Thread t5 = new Thread(findAmountOfLetters);
        t5.start();


        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (Map.Entry<Character, Integer> entry : findAmountOfLetters.getLettersCount().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
