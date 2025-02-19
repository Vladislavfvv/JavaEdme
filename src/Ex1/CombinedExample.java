package Ex1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public class CombinedExample {
    private static final AtomicInteger count = new AtomicInteger(0);
    private static final  AtomicInteger count2 = new AtomicInteger(0);



    public static void main(String[] args) throws InterruptedException {
        int numberOfTasks = 100_000;
        CountDownLatch latch = new CountDownLatch(numberOfTasks);
        ExecutorService executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < numberOfTasks; i++) {
            executor.submit(() -> {
                try {
                    count.incrementAndGet();
                    count2.incrementAndGet();
                    //System.out.println(Thread.currentThread().getName() + " выполняет задачу.");
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // Ожидание завершения всех задач
        //System.out.println("Все задачи выполнены.");
        System.out.println(count);
        System.out.println(count2);


        executor.shutdown();
    }
}