package com.neueda.blocking.chassis.client;

import com.neueda.blocking.chassis.exception.CustomException;
import com.neueda.blocking.chassis.properties.ClientProperties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.UriBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Function;

import static com.neueda.blocking.chassis.constants.ChassisConstants.CLIENT_URL;
import static org.springframework.web.util.UriComponentsBuilder.fromUri;

@Slf4j
public class ClientHelper {

    private final HttpClient httpClient;
    private final URI baseUrl;

    ClientHelper(ClientProperties clientProps) {
        this.httpClient = HttpClient.newHttpClient();
        this.baseUrl = clientProps.baseUrl();
    }

    HttpResponse<?> performGetRequest(Function<UriBuilder, URI> uriFunction) {
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
            throw  new CustomException( e.getMessage(), CLIENT_URL);
        }
    }
}