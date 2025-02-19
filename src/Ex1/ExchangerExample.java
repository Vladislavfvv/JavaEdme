package Ex1;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        // Поток для нечетных чисел
        Thread oddThread = new Thread(() -> {
            for (int i = 1; i <= 9; i += 2) {
                try {
                    System.out.println(i); // Выводим нечетное число
                    exchanger.exchange(i); // Обмениваемся данными с другим потоком
                //При вызове exchange(i) поток блокируется и ждет, пока другой поток (в данном случае поток для четных чисел) не вызовет exchange().
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Поток для четных чисел
        Thread evenThread = new Thread(() -> {
            for (int i = 2; i <= 10; i += 2) {
                try {
                    exchanger.exchange(null); // Ожидаем данные от другого потока
                    //В данном случае поток для четных чисел не передает никаких полезных данных потоку для нечетных чисел. Ему нужно только дождаться, пока поток для нечетных чисел передаст ему данные.
                    //
                    //Поэтому в качестве значения передается null. Это просто "пустышка", чтобы синхронизировать потоки.
                    System.out.println(i); // Выводим четное число
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        oddThread.start();
        evenThread.start();

        // Ждем завершения потоков
        try {
            oddThread.join();
            evenThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
