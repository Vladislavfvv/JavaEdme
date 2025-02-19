package synchronize;

public class SynchronizeBlock {
    public static void main(String[] args){
        MyRunnableImpl2 runnable = new MyRunnableImpl2();
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }


}
class Counter{
    static int count = 0;
}
class MyRunnableImpl2 implements Runnable {
    //public synchronized void doWork(){
    public void doWork() {
        synchronized(this) {
            Counter.count++;
            System.out.println(Counter.count);

        }
    }
    @Override
    public void run(){
        for(int i = 0; i < 10; i++){
            doWork();
        }
    }
}