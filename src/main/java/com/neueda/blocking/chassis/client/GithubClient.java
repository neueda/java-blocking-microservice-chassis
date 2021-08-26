package com.neueda.blocking.chassis.client;

import com.neueda.blocking.chassis.properties.ClientProperties;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

@Service
public class GithubClient {

    private final ClientHelper clientHelper;
    private final String baseUrl;

    public GithubClient(ClientProperties props) {
        HttpClient httpClient = HttpClient.newHttpClient();
        this.clientHelper = new ClientHelper(httpClient);
        this.baseUrl = props.baseUrl().toString();
    }

    public HttpResponse<String> searchUsernameContaining(@NonNull String value) throws IOException, InterruptedException {

        return clientHelper.performGetRequest(URI.create(baseUrl + "/search/users?q=" + value + "+repos:%3E0"));
    }
}
