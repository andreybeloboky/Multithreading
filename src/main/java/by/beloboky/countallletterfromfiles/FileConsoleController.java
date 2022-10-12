package by.beloboky.countallletterfromfiles;

import java.util.Map;


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
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        for (Map.Entry<Character, Integer> entry : countLettersService.getLettersCount().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
