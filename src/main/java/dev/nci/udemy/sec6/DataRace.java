package dev.nci.udemy.sec6;

public class DataRace {
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
        private int x = 0;
        private int y = 0;

        /**
         * use `synchronized` does not help here!!!
         * have to add `synchronized` to `checkForDataRace` then it will work!
         */
        public synchronized void increment() {
            // There is no guarantee that which line will execute first
            // for Compiler and CPU optimization
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
