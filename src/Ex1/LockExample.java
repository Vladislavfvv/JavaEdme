package Ex1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    public static Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {


        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 10; i += 2) {
                lock.lock();// Захватываем блокировку
                try {
                    System.out.println(i);
                    condition.signal(); // Уведомляем другой поток
                    if (i < 9) { // Не ждем после последнего числа
                        condition.await(); // Ожидаем уведомления
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();// Освобождаем блокировку
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 2; i <= 10; i += 2) {
                lock.lock(); // Захватываем блокировку
                try {
                    System.out.println(i); // Выводим число
                    condition.signal(); // Уведомляем другой поток
                    if (i < 10) { // Не ждем после последнего числа
                        condition.await(); // Ожидаем уведомления
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock(); // Освобождаем блокировку
                }
            }
        });


        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        // Разбудим потоки после завершения если они ждут
        try {
            lock.lock();
            condition.signalAll();
        }
        finally{
            lock.unlock();
        }
    }
}


