package com.neueda.blocking.chassis.client;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class ClientHelper {

    private final HttpClient httpClient;

    HttpResponse<String> performGetRequest(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(uri)
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
