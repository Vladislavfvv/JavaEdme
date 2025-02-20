package Multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class EvenAndOddSynchronize {
    public static final List<Integer> listNumbers = new ArrayList<>();
    public static final Object lock1 = new Object();
    public static boolean isFlag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            synchronized(lock1) {
                for(int i = 1; i <= 10; i += 2) {
                    while(isFlag) {
                        try {
                            lock1.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    listNumbers.add(i);
                    System.out.println(i);
                    isFlag = true;
                    lock1.notify();
                }

            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized(lock1) {
                for(int i = 2; i <= 10; i += 2) {
                    while(!isFlag) {
                        try {
                            lock1.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    listNumbers.add(i);
                    System.out.println(i);
                    isFlag = false;
                    lock1.notify();
                }

            }
        });
        thread1.start();
        thread2.start();
    }
}
