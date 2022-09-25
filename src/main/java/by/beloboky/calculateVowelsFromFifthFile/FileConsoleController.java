package by.beloboky.calculateVowelsFromFifthFile;

public class FileConsoleController {

    public static void main(String[] args) {
        CalculateVowelsService calculateLettersFirst = new CalculateVowelsService("FirstFile");
        CalculateVowelsService calculateLettersSecond = new CalculateVowelsService("SecondFile");
        CalculateVowelsService calculateLettersThird = new CalculateVowelsService("ThirdFile");
        CalculateVowelsService calculateLettersFourth = new CalculateVowelsService("FourthFile");
        CalculateVowelsService calculateLettersFifth = new CalculateVowelsService("FiveFile");
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
