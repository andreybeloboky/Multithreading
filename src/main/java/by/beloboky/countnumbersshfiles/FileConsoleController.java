package by.beloboky.countnumbersshfiles;

public class FileConsoleController {
    public static void main(String[] args) {
        CountService countService = new CountService();
        Thread t1 = new Thread(countService);
        Thread t2 = new Thread(countService);
        Thread t3 = new Thread(countService);
        Thread t4 = new Thread(countService);
        Thread t5 = new Thread(countService);
        Thread t6 = new Thread(countService);
        Thread t7 = new Thread(countService);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Amount of .sh files are " + countService.getCount() + "th thing");
    }
}
