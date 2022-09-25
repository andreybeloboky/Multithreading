package by.beloboky.countNumbersShFiles;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class CountService implements Runnable {
    private final LinkedList<String> queueList = new LinkedList<>();
    private volatile int count = 0;

    public CountService() {
        FileRepository list = new FileRepository();
        List<String> arr = list.listFiles();
        this.queueList.addAll(arr);
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
    public synchronized void run() {
        int i = 0;
        while (i < queueList.size()) {
            if (Objects.requireNonNull(queueList.pollFirst()).endsWith(".sh")) {
                count++;
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
