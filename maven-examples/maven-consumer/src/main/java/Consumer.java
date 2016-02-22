/**
 * Created by Vytautas on 2016-01-18.
 */
public class Consumer {
    public static void main(String[] args) {
        new ModuleA().testB();
        new ModuleB().test();
        new ModuleC().test();
    }
}
