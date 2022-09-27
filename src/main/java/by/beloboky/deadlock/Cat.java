package by.beloboky.deadlock;

public class Cat {

    private final String name;

    public Cat(String name) {
        this.name = name;
    }

    public synchronized void methodOne(Cat cat) {
        System.out.format("%s: %s test" + "%n", this.name, cat.getName());
        cat.methodTwo(this);
    }

    public synchronized void methodTwo(Cat cat) {
        System.out.format("%s: %s" + "%n", this.name, cat.getName());
    }

    public String getName() {
        return name;
    }
}
