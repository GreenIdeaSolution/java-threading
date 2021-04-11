package dev.nci.udemy.sec3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainJoin {
    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(0L, 3435L, 35343L, 2324L, 4646L, 23L, 5556L);
        List<FactorialThread> threads = new ArrayList<>();
        for (long inputNumber : inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        for (Thread thread : threads) {
            thread.setDaemon(true);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join(2000);
        }

        for (int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread ft = threads.get(i);
            if (ft.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + ft.getResult());
            } else {
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }
    }
}
