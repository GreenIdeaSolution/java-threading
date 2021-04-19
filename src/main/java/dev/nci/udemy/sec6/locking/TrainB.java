package dev.nci.udemy.sec6.locking;

import java.util.Random;

public class TrainB implements Runnable {
    private final Intersection intersection;
    private final Random random = new Random();

    public TrainB(Intersection intersection) {
        this.intersection = intersection;
    }

    @SuppressWarnings("all")
    @Override
    public void run() {
        while (true) {
            long sleepingTime = random.nextInt(100);
            try {
                Thread.sleep(sleepingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            intersection.takeRoadB();
        }
    }
}
