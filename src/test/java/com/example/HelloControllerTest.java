package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
public class HelloControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    public void testHello() {
        MutableHttpRequest<?> request = HttpRequest.GET("/").accept(MediaType.TEXT_PLAIN);
        String body = httpClient.toBlocking().retrieve(request);

        Assertions.assertNotNull(body);
        Assertions.assertEquals("Hello World!", body);
    }

}