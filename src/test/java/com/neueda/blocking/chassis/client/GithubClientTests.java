package com.neueda.blocking.chassis.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.neueda.blocking.chassis.exception.CustomException;
import com.neueda.blocking.chassis.properties.ClientProperties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.net.URI;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static java.lang.String.format;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@AutoConfigureWireMock
public class GithubClientTests {

    private static GithubClient client;

    @BeforeAll
    static void init(@Autowired WireMockServer server){
        URI baseUri = URI.create(server.baseUrl());
        client = new GithubClient(new ClientProperties(baseUri));
    }

    @Test
    @DisplayName("Should return no user found")
    void shouldReturnNoUsersFound() throws CustomException {
        //given
        var testValue = "testuser";
        var testUrl = format("/search/users?q=%s+repos:%%3E0", testValue);
        var expected = "{\"total_count\":0,\"incomplete_results\":false,\"items\":[]}";
        WireMock.stubFor(
                WireMock.get(testUrl)
                        .willReturn(aResponse()
                                .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                                .withBody(expected))
        );

        //when
        client.searchUsernameContaining(testValue);

        //then
        verify(getRequestedFor(urlEqualTo(testUrl)));

    }

}