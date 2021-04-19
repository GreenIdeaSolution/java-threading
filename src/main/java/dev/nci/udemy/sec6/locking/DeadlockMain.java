package dev.nci.udemy.sec6.locking;

public class DeadlockMain {
    public static void main(String[] args) {
        Intersection intersection = new Intersection();

        Thread t1 = new Thread(new TrainA(intersection));
        Thread t2 = new Thread(new TrainB(intersection));

        t1.start();
        t2.start();
    }
}
