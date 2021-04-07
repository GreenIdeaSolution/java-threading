package dev.nci.udemy.sec2;

public class Vault {
    private final int password;

    public Vault(int password) {
        this.password = password;
    }

    public boolean isCorrectPassword(int guess) {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this.password == guess;
    }
}
