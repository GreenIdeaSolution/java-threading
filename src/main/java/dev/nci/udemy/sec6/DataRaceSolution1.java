package dev.nci.udemy.sec6;

public class DataRaceSolution1 {
    public static void main(String[] args) {
        SharedClass sharedClass = new SharedClass();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.checkForDataRace();
            }
        });

        t1.start();
        t2.start();
    }

    public static class SharedClass {
        private volatile int x = 0;
        private volatile int y = 0;

        public void increment() {
            // Use `volatile` is enough, as it guarantees the order of the below statements
            // even it is not `atomic`:
            x++;
            y++;
        }

        public void checkForDataRace() {
            if (y > x) {
                throw new RuntimeException("y > x - Data Race is detected");
            }
        }
    }
}
