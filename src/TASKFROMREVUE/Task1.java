package TASKFROMREVUE;

import java.util.concurrent.CyclicBarrier;

public class Task1 {


}

class Programmer extends Thread {
    private String name;
    private CyclicBarrier barrier;

    Programmer(String name, CyclicBarrier barrier) {
        this.name = name;
        this.barrier = barrier;
    }

    @Override
    public void run() {


//        try {
//            barrier .await();
//            System.out.println("Starting " + name);
//
//        }
    }
}
