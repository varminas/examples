package lt.arminai.helper;

/**
 * Created by vytautas on 2016-09-29.
 */
public final class ThreadUtils {
    public static void wait(Object object) {
        try {
            object.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}
