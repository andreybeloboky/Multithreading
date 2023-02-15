package by.beloboky.deadlock;

public class Deadlock {
    public static void main(String[] args) {
        CatOne cat = new CatOne("Alisa");
        CatTwo cat2 = new CatTwo("Wystrik");
        cat.catTwo = cat2;
        cat2.catOne = cat;
        Thread t1 = new Thread(cat::methodOne);
        Thread t2 = new Thread(cat2::methodOne);
        t1.start();
        t2.start();
    }
}