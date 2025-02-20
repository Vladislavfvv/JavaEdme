package Multithreading;


import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Есть 5 программистов: Андрей, Артур, Геннадий, Мурад, Влад.
 * Каждый параллельно пишет микросервис
 * После этого они одновременно выводятся в прод
 * Необходимо написать код, где, начиная написание микросервиса выводится сообщение:
 * "{имя} начал писать сервис"
 * "{имя} закончил писать сервис"
 * После завершения всеми написания, выводим сообщение:
 * "Микросервисы выведены в прод и всё пошло не так."
 * После все точно так же устраняют баги на своих сервисах и вновь одновременно выводят в прод.
 **/

public class CyclicBarrierEx {
    public static List<String> listString = List.of("Андрей", "Артур", "Геннадий", "Мурад", "Влад");

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("Микросервисы выведены в прод и всё пошло не так");
        });

        Thread thread1 = new Thread(new MyThread(listString.get(0), cyclicBarrier));
        Thread thread2 = new Thread(new MyThread(listString.get(1), cyclicBarrier));
        Thread thread3 = new Thread(new MyThread(listString.get(2), cyclicBarrier));
        Thread thread4 = new Thread(new MyThread(listString.get(3), cyclicBarrier));
        Thread thread5 = new Thread(new MyThread(listString.get(4), cyclicBarrier));

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
    }


    public static class MyThread extends Thread {
        private final CyclicBarrier cyclicBarrier;
        private final String threadName;

        public MyThread(String threadName, CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
            this.threadName = threadName;
            start();
        }

        @Override
        public void run() {
            System.out.println(threadName + " начал писать сервис");
            try {
                Thread.sleep((long) Math.random() * 3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " закончил писать сервис");
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }


            System.out.println(threadName + " устраняет баги ни своём сервисе");
            try {
                Thread.sleep((long) Math.random() * 2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " закончил устранять баги ни своём сервисе");
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
