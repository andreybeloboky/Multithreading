package by.beloboky.countnumbersshfiles;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class CountService implements Runnable {
    private final LinkedList<String> queueList;
    private int count;

    public CountService() {
        FileRepository list = new FileRepository();
        List<String> arr = list.listFiles();
        this.queueList = new LinkedList<>();
        this.queueList.addAll(arr);
        this.count = 0;
    }

    /**
     * @return - string from list if it isn't null;
     */
    private synchronized String init() {
        String s = queueList.pollFirst();
        return Objects.requireNonNullElseGet(s, () -> queueList.pollFirst() + 1);
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        int i = 0;
        while (i < queueList.size()) {
            if (this.init().endsWith(".sh")) {
                this.count++;
            }
        }
    }

    /**
     * @return - amount of .sh files.
     */
    public int getCount() {
        return count;
    }
}
