package com.neueda.blocking.chassis.client;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.blocking.chassis.properties.ClientProperties;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriBuilder;
import static org.springframework.web.util.UriComponentsBuilder.fromUri;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Function;

@Slf4j
public class ClientHelper {

    private final HttpClient httpClient;
    private final URI baseUrl;
    private final ObjectMapper objectMapper;

    ClientHelper(ClientProperties clientProps) {
        this.httpClient = HttpClient.newHttpClient();
        this.baseUrl = clientProps.baseUrl();
        this.objectMapper = new ObjectMapper();
    }

    <T> T performGetRequest(Function<UriBuilder, URI> uriFunction, Class<T> clazz) {

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(uriFunction.apply(fromUri(baseUrl)))
                    .build();
            String response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();

            return clazz.isAssignableFrom(String.class) ? (T) response : objectMapper.readValue(response, clazz);
        }
        catch (IOException | InterruptedException e) {
            log.error("Failed to send GET request", e);
            throw new RestClientException(e.getMessage(), e);
        }
    }

}