package dev.nci.udemy.sec3;

public class BlockingTask implements Runnable {
    @Override
    public void run() {
        // do something
        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            // e.printStackTrace();
            System.out.println("Exiting blocking thread");
        }
    }
}
