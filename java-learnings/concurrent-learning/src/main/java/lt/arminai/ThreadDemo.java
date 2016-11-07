package lt.arminai;

/**
 * Created by vytautas on 2016-11-07.
 */
public class ThreadDemo implements Runnable {
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() " + t.getName());
        System.out.println("run() status = " + t.isAlive());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        Thread t = new Thread(new ThreadDemo());
        t.start();
        t.join();
        System.out.println("main() " + t.getName());
        System.out.println("main() status = " + t.isAlive());
    }
}
