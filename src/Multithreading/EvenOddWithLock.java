package Multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EvenOddWithLock {
    public static List<Integer> listNumbers = new ArrayList<>();

    public EvenOddWithLock() {
    }

    public static void main(String[] args) throws InterruptedException {
        FirstThread firstThread = new FirstThread();
        SecondThread secondThread = new SecondThread();
        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();

        for(int i = 1; i <= 10; ++i) {
            if (i % 2 != 0) {
                firstThread.num = i;
                listNumbers.add(firstThread.num);
            } else {
                secondThread.num = i;
                listNumbers.add(secondThread.num);
            }
        }

        for(Integer listNumber : listNumbers) {
            System.out.println(listNumber);
        }

    }

    public static class FirstThread extends Thread {
        public int num;

        public FirstThread() {
        }

        public void run() {
        }
    }

    public static class SecondThread extends Thread {
        public int num;

        public SecondThread() {
        }

        public void run() {
        }
    }
}
