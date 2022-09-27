package by.beloboky.countAllLetterFromFiles;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FileConsoleController {

    public static void main(String[] args) {
            CountLettersService countLettersService = new CountLettersService();
            Thread t1 = new Thread(countLettersService);
            t1.start();
            Thread t2 = new Thread(countLettersService);
            t2.start();
            Thread t3 = new Thread(countLettersService);
            t3.start();
            Thread t4 = new Thread(countLettersService);
            t4.start();
            Thread t5 = new Thread(countLettersService);
            t5.start();

            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println();
            for (Map.Entry<Character, Integer> entry : countLettersService.getLettersCount().entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
}
