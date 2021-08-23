package com.neueda.blocking.chassis.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import com.neueda.blocking.chassis.properties.ClientProperties;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class GithubClient {

    private final ClientHelper clientHelper;

    public GithubClient(ClientProperties clientProps) {
        this.clientHelper = new ClientHelper(clientProps);
    }

    public String searchUsernameContaining(@NonNull String value) {

        HttpResponse<String> response = clientHelper.performGetRequest(uriBuilder -> uriBuilder
                .pathSegment("search")
                .pathSegment("users")
                .queryParam("q", value.concat("+repos:>0"))
                .build());
        return response.body();
    }
}