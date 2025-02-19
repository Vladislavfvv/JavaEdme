package Ex1;

import javafx.scene.layout.Priority;

public class StartThread implements Runnable {
    @Override
    public void run() {
        for (int i = 1000; i > 0; i--) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Thread thread = new Thread(new StartThread());
        thread.setName("Thread StartThread()");
        thread.start();
       new Thread(new Runnable() {
            @Override
            public void run() {
             for (int i = 0; i < 1000; i++) {
                Thread.currentThread().setName("Thread StartThreadAnonimixer");
                 System.out.println(Thread.currentThread().getName() + " " + i);
             }
            }
        }).start();

       Thread thread3 =  new Thread(() -> System.out.println("Another thread"));
       thread3.setName("Another thread3");
       thread3.setPriority(Thread.MAX_PRIORITY);
       thread3.start();

    }
}
