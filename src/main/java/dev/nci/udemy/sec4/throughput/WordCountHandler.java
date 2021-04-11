package dev.nci.udemy.sec4.throughput;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class WordCountHandler implements HttpHandler {
    private final String content;

    public WordCountHandler(String content) {
        this.content = content;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String query = exchange.getRequestURI().getQuery();
        String[] keyValue = query.split("=");
        String action = keyValue[0];
        String word = keyValue[1];
        if (!action.equals("word")) {
            exchange.sendResponseHeaders(400, 0);
            return;
        }

        long count = countWord(word);
        byte[] response = Long.toString(count).getBytes();
        exchange.sendResponseHeaders(200, response.length);
        OutputStream os = exchange.getResponseBody();
        os.write(response);
        os.close();
    }

    private long countWord(String word) {
        long count = 0;
        int index = 0;
        while (index >= 0) {
            index = content.indexOf(word, index);
            if (index >= 0) {
                count++;
                index++;
            }
        }

        return count;
    }
}
