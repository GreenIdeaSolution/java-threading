package dev.nci.udemy.sec4;

import dev.nci.udemy.sec4.throughput.ThroughputHttpServer;

import java.io.IOException;

public class MainJmeter {
    public static void main(String[] args) throws IOException {
        ThroughputHttpServer.startServer();
    }
}
