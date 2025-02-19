package Multithreading;

import java.util.concurrent.Semaphore;

public class Parking {
    private static final boolean[] PARKING_PLACES = new boolean[5];
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);

    public Parking() {
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 1; i <= 7; ++i) {
            (new Thread(new Car(i))).start();
            Thread.sleep(400L);
        }

    }

    public static class Car implements Runnable {
        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        public void run() {
            System.out.printf("Автомобиль №%d подъехал к парковке.\n", this.carNumber);

            try {
                Parking.SEMAPHORE.acquire();
                int parkingNumber = -1;
                synchronized(Parking.PARKING_PLACES) {
                    for(int i = 0; i < 5; ++i) {
                        if (!Parking.PARKING_PLACES[i]) {
                            Parking.PARKING_PLACES[i] = true;
                            parkingNumber = i;
                            System.out.printf("Автомобиль №%d припарковался на месте %d.\n", this.carNumber, i);
                            break;
                        }
                    }
                }

                Thread.sleep(5000L);
                synchronized(Parking.PARKING_PLACES) {
                    Parking.PARKING_PLACES[parkingNumber] = false;
                }

                Parking.SEMAPHORE.release();
                System.out.printf("Автомобиль №%d покинул парковку.\n", this.carNumber);
            } catch (InterruptedException var8) {
            }

        }
    }
}

