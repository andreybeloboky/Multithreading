package by.beloboky.deadlock;

public class CatTwo {
    CatOne catOne;
    private final String name;

    public CatTwo(String name) {
        this.name = name;
    }

    public synchronized void methodOne() {
        System.out.format("%s test" + "%n", this.name);
        catOne.methodTwo();
    }

    public synchronized void methodTwo() {
        System.out.println("two");
    }
}
