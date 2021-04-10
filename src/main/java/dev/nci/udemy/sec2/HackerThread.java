package dev.nci.udemy.sec2;

public abstract class HackerThread extends Thread {
    public static final int MAX_PASSWORD = 9999;

    protected Vault vault;

    public HackerThread(Vault vault) {
        this.vault = vault;
        this.setName(this.getClass().getSimpleName());
    }
}
