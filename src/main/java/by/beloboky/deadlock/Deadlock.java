package by.beloboky.deadlock;

public class Deadlock {
    public static void main(String[] args) {
        Cat cat = new Cat("Alisa");
        Cat cat2 = new Cat("Wystrik");
        Thread t1 = new Thread(() -> cat.methodOne(cat2));
        Thread t2 = new Thread(() -> cat2.methodOne(cat));
        t1.start();
        t2.start();
    }
}
