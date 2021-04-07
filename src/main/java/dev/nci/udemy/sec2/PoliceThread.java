package dev.nci.udemy.sec2;

public class PoliceThread extends Thread {
    @Override
    public void run() {
        for (int i = 10; i > 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i + " seconds");
        }
        System.out.println("Game over for you hackers");
        System.exit(0);
    }
}
