package by.beloboky.countvowelsfromfifthfile;

public class FileConsoleController {

    public static void main(String[] args) {
        CountVowelsService calculateLetters = new CountVowelsService();
        Thread t1 = new Thread(calculateLetters, "Thread-1");
        Thread t2 = new Thread(calculateLetters, "Thread-2");
        Thread t3 = new Thread(calculateLetters, "Thread-3");
        Thread t4 = new Thread(calculateLetters, "Thread-4");
        Thread t5 = new Thread(calculateLetters, "Thread-5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(calculateLetters.getCount());
    }
}
