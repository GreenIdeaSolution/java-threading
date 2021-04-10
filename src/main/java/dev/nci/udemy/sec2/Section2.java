package dev.nci.udemy.sec2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Section2 {
    public static void main(String[] args) {
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(HackerThread.MAX_PASSWORD));

        List<Thread> threads = Arrays.asList(new AscendingHackerThread(vault),
                new DescendingHackerThread(vault),
                new PoliceThread());

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
