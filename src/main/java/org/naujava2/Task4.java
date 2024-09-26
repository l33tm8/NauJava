package org.naujava2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Task4 {
    public static void main(String[] args) {
        CompletableFuture<HttpResponse<String>> response;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/headers"))
                    .build();
            response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        }

        response.thenAccept(resp ->
        {
            ObjectMapper mapper = new ObjectMapper();
            try (FileOutputStream fos = new FileOutputStream("Task4Output.txt")) {
                TypeReference<Map<String, Map<String, String>>> typeRef = new TypeReference<>() {};
                Map<String, Map<String, String>> body;
                body = mapper.readValue(resp.body(), typeRef);
                fos.write(body.get("headers").toString().getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }).join();

        response.exceptionally(e ->
        {
            System.out.println(e.getMessage());
            return null;
        });
    }
}
