package dev.nci.udemy.sec6;

public class AtomicTest {
    public static void main(String[] args) {
        SharedClass sharedClass = new SharedClass();
        Thread t1 = new Thread(sharedClass::increment);
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("\nStep " + i + ":");
                System.out.println("count = " + sharedClass.getCount());
                System.out.println("average = " + sharedClass.getAverage());
            }
        });

        t1.start();
        t2.start();
    }

    /**
     * Volatile only help to avoid `Data race`
     * but cannot guarantee `getCount()` and `getAverage()`
     * can avoid `Race condition`
     */
    public static class SharedClass {
        private long count = 0;
        private long average = 0;

        public synchronized void increment() {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count = 100011111134345L;
            // sleep the executing thread
            // to wait and see if this `method` is `atomic`:
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            average = 9000000004543L;
        }

        public long getCount() {
            return count;
        }

        public long getAverage() {
            return average;
        }
    }
}
