package com.neueda.blocking.chassis.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.neueda.blocking.chassis.exception.CustomException;
import com.neueda.blocking.chassis.properties.ClientProperties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static java.lang.String.format;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class GithubClientTests {

    private static GithubClient client;

    private static final int PORT = 8080;
    private static final String HOST = "localhost";

    private static WireMockServer server = new WireMockServer(PORT);

    @BeforeAll
    public static void setup() {

        server.start();
        WireMock.configureFor(HOST, PORT);
        URI baseUri = URI.create(server.baseUrl());
        client = new GithubClient(new ClientProperties(baseUri));

    }

    @Test
    @DisplayName("Should return no user found")
    void shouldReturnNoUsersFound() throws CustomException {

        var testValue = "testuser";
        var testUrl = format("/search/users?q=%s+repos:%%3E0", testValue);
        var expected = "{\"total_count\":0,\"incomplete_results\":false,\"items\":[]}";
        WireMock.stubFor(
                WireMock.get(testUrl)
                        .willReturn(aResponse()
                                .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                                .withBody(expected))
        );

        client.searchUsernameContaining(testValue);
        verify(getRequestedFor(urlEqualTo(testUrl)));

    }
    @AfterAll
    public static void teardown() {
        if(null != server && server.isRunning()){
            server.shutdownServer();
        }
    }

}