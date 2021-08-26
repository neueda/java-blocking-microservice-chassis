package com.neueda.blocking.chassis.client;

import com.neueda.blocking.chassis.exception.CustomException;
import com.neueda.blocking.chassis.properties.ClientProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.util.UriBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Function;

import static com.neueda.blocking.chassis.constants.ChassisConstants.CLIENT_URL;
import static org.springframework.web.util.UriComponentsBuilder.fromUri;

@RequiredArgsConstructor
public class ClientHelper {

    private final HttpClient httpClient;
    private final URI baseUrl;

    ClientHelper(ClientProperties clientProps) {
        this.httpClient = HttpClient.newHttpClient();
        this.baseUrl = clientProps.baseUrl();
    }

    <T> T performGetRequest(Function<UriBuilder, URI> uriFunction) {
        T res = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(uriFunction.apply(fromUri(baseUrl)))
                    .build();
            res =  (T) httpClient.send(request,HttpResponse.BodyHandlers.ofString());

        }
        catch (IOException | InterruptedException e) {

            throw new CustomException(e.getMessage(), CLIENT_URL);
        }

        return res;
    }
}
