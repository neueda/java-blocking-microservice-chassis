package com.neueda.blocking.chassis.client;

import com.neueda.blocking.chassis.properties.ClientProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.util.UriBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Function;

import static org.springframework.web.util.UriComponentsBuilder.fromUri;

@RequiredArgsConstructor
@Getter
public class ClientHelper {

    private final HttpClient httpClient;
    private final URI baseUrl;

    ClientHelper(ClientProperties clientProps) {
        this.httpClient = HttpClient.newHttpClient();
        this.baseUrl = clientProps.baseUrl();
    }

    HttpResponse<String> performGetRequest(Function<UriBuilder, URI> uriFunction) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(uriFunction.apply(fromUri(baseUrl)))
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
