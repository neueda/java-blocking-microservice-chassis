package com.neueda.blocking.chassis.client;
import com.neueda.blocking.chassis.exception.CustomException;
import com.neueda.blocking.chassis.properties.ClientProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class ClientHelper {

    private final HttpClient httpClient;
    private final URI baseUrl;

    ClientHelper(ClientProperties clientProps) {
        this.httpClient = HttpClient.newHttpClient();
        this.baseUrl = clientProps.baseUrl();
    }

    HttpResponse<?> performGetRequest(Function<UriBuilder, URI> uriFunction) throws CustomException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(uriFunction.apply(fromUri(baseUrl)))
                    .build();

            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        }

        catch (IOException | InterruptedException e) {
            log.error("this thread is interrupted or i/o error", e);
            return (HttpResponse<?>) new CustomException("An error has occurred", e, "v1/chassisClientNameContain");
        }
    }
}