package by.beloboky.deadlock;

public class CatOne {
    CatTwo catTwo;
    private final String name;

    public CatOne(String name) {
        this.name = name;
    }

    public synchronized void methodOne() {
        System.out.format("%s test" + "%n", this.name);
        catTwo.methodTwo();
    }

    public synchronized void methodTwo() {
        System.out.println("one");
    }
}
