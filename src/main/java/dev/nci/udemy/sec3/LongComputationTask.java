package dev.nci.udemy.sec3;

import java.math.BigInteger;

public class LongComputationTask implements Runnable {
    private BigInteger base;
    private BigInteger power;

    public LongComputationTask(BigInteger base, BigInteger power) {
        this.base = base;
        this.power = power;
    }

    @Override
    public void run() {
        System.out.println(base + "^" + power + " = " + pow(base, power));
    }

    private BigInteger pow(BigInteger base, BigInteger power) {
        BigInteger result = BigInteger.ONE;

        for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0 ; i = i.add(BigInteger.ONE)) {
            if (Thread.currentThread().isInterrupted()) {
                return BigInteger.ZERO;
            }
            result = result.multiply(base);
        }

        return result;
    }
}
