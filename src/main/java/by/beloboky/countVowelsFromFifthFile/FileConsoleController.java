package by.beloboky.countVowelsFromFifthFile;

public class FileConsoleController {

    public static void main(String[] args) {
        CountVowelsService calculateLettersFirst = new CountVowelsService("FirstFile");
        CountVowelsService calculateLettersSecond = new CountVowelsService("SecondFile");
        CountVowelsService calculateLettersThird = new CountVowelsService("ThirdFile");
        CountVowelsService calculateLettersFourth = new CountVowelsService("FourthFile");
        CountVowelsService calculateLettersFifth = new CountVowelsService("FiveFile");
        Thread t1 = new Thread(calculateLettersFirst, "Thread-1");
        Thread t2 = new Thread(calculateLettersSecond, "Thread-2");
        Thread t3 = new Thread(calculateLettersThird, "Thread-3");
        Thread t4 = new Thread(calculateLettersFourth, "Thread-4");
        Thread t5 = new Thread(calculateLettersFifth, "Thread-5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int sum = calculateLettersFirst.getCount() + calculateLettersSecond.getCount() + calculateLettersThird.getCount() + calculateLettersFourth.getCount() + calculateLettersFifth.getCount();
        System.out.println(sum);
    }
}
