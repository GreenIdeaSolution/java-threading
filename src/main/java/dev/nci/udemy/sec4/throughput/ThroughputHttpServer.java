package dev.nci.udemy.sec4.throughput;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThroughputHttpServer {
    private static final String INPUT_FILE = "sec4/war_and_peace.txt";
    private static final int NUMBER_OF_THREADS = 2;

    private static String content;

    public static void startServer() throws IOException {
        // read content
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(INPUT_FILE);
        content = new String(Files.readAllBytes(Paths.get(Objects.requireNonNull(url).getPath())));

        // start HttpServer
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/search", new WordCountHandler(content));
        Executor executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        server.setExecutor(executor);
        server.start();
    }

}
