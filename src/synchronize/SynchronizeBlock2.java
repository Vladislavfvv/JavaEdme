package synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static synchronize.SynchronizeBlock2.counter;
import static synchronize.SynchronizeBlock2.increment;

public class SynchronizeBlock2 {
    static int counter = 0;


    public static void increment() {
        synchronized (SynchronizeBlock2.class) {
            counter++;
        }
    }

    public static void main (String[] args) throws InterruptedException {
        R r = new R();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        ExecutorService executor2 = Executors.newCachedThreadPool();
    }
}

class R implements Runnable{
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            increment();
            System.out.println(counter);
        }
    }
}
