package dev.nci.udemy.sec3;

import java.math.BigInteger;

public class Section3 {
    public static void main(String[] args) throws InterruptedException {
        // already handle `interrupt` exception:
        Thread thread = new Thread(new BlockingTask());
        thread.start();
        Thread.sleep(100);
        thread.interrupt(); // interrupt a `sleeping` thread.

        // long computation + must be handled `interrupt`:
        thread = new Thread(new LongComputationTask(new BigInteger("2"), new BigInteger("10")));
        thread.start();
        Thread.sleep(100);
        thread.interrupt(); // interrupt a `running` thread, then must be handled.

        // a `deamon` (run in background) and does not terminated even main thread is terminated:
        thread = new Thread(new LongComputationTask2(new BigInteger("2"), new BigInteger("10")));
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(100);
        thread.interrupt(); // no effected, as we do not handle it.
    }
}
