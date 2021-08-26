package com.neueda.blocking.chassis.client;

import com.neueda.blocking.chassis.properties.ClientProperties;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public class GithubClient {

    private final ClientHelper clientHelper;

    public GithubClient(ClientProperties clientProps) {
        this.clientHelper = new ClientHelper(clientProps);
    }

    public String searchUsernameContaining(@NonNull String value) {

        HttpResponse<?> response = clientHelper.performGetRequest(uriBuilder -> uriBuilder
                .pathSegment("search")
                .pathSegment("users")
                .queryParam("q", value.concat("+repos:>0"))
                .build());
        return response.body().toString();
    }
}
