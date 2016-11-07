package lt.arminai;

/**
 * Created by vytautas on 2016-11-07.
 */
public class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread started:::" + Thread.currentThread().getName());

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread ended:::" + Thread.currentThread().getName());
    }
}
