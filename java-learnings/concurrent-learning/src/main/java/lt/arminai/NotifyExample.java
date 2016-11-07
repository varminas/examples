package lt.arminai;

/**
 * Created by vytautas on 2016-11-07.
 */
public class NotifyExample {
    public static void main(String[] args) {
        ThreadB b = new ThreadB();
        b.start();
        synchronized (b) {
            try {
                System.out.println("Waiting for b to complete");
                b.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Total is: " + b.getTotal());
        }
    }
}

class ThreadB extends Thread {
    private int total;
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                total += i;
            }
            notify();
        }
    }

    public int getTotal() {
        return total;
    }
}
