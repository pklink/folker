package com.example.releases.http;

import com.example.releases.domain.Medium;
import com.example.releases.domain.Release;
import com.example.releases.domain.ReleaseRepository;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MicronautTest
public class ReleaseControllerTest {

    @Inject
    private ReleaseRepository releaseRepository;

    @Inject
    @Client("/")
    HttpClient client;

    @MockBean(ReleaseRepository.class)
    ReleaseRepository releaseRepository() {
        return mock(ReleaseRepository.class);
    }

    @Test
    void getAllReleases_shouldReturnAllReleases() {
        // arrange
        Release release1 = new Release("1", "Album 1", "Artist 1", Medium.CD);
        Release release2 = new Release("2", "Album 2", "Artist 2", Medium.VINYL);
        List<Release> releases = List.of(release1, release2);

        when(releaseRepository.findAll()).thenReturn(releases);

        // act
        HttpRequest<?> request = HttpRequest.GET("/releases");
        HttpResponse<ListReleaseResponse> response = client
                .toBlocking()
                .exchange(request, ListReleaseResponse.class);

        // assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.status());

        assertNotNull(response.body());
        assertEquals(2, response.body().items().size());
        assertEquals("Album 1", response.body().items().getFirst().title());
        assertEquals("Artist 1", response.body().items().getFirst().albumArtist());
        assertEquals(Medium.CD, response.body().items().getFirst().medium());
        assertEquals("Album 2", response.body().items().get(1).title());
        assertEquals("Artist 2", response.body().items().get(1).albumArtist());
        assertEquals(Medium.VINYL, response.body().items().get(1).medium());

        // Verify repository was called
        verify(releaseRepository).findAll();
    }

    @Test
    void create_shouldCreateAndReturnRelease() {
        // arrange
        CreateReleaseRequest requestBody = new CreateReleaseRequest("New Album", "New Artist", Medium.CD);
        Release savedRelease = new Release("1", "New Album", "New Artist", Medium.CD);

        when(releaseRepository.save(any(Release.class))).thenReturn(savedRelease);

        // act
        HttpRequest<?> request = HttpRequest.POST("/releases", requestBody);
        HttpResponse<Release> result = client.toBlocking().exchange(request, Release.class);

        // assert
        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.status());

        assertNotNull(result.body());
        assertEquals("1", result.body().id());
        assertEquals("New Album", result.body().title());
        assertEquals("New Artist", result.body().albumArtist());
        assertEquals(Medium.CD, result.body().medium());
    }

    @Test
    void create_shouldReturn400_whenRequestIsInvalid() {
        // arrange
        Map<String, Object> invalidRequest = Map.of(
                // Missing title field
                "albumArtist", "Artist Name",
                "medium", Medium.CD
        );

        HttpRequest<?> request = HttpRequest.POST("/releases", invalidRequest);

        // act & assert
        HttpClientResponseException result = assertThrows(
                HttpClientResponseException.class,
                () -> client.toBlocking().exchange(request, Release.class)
        );

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatus());
    }
}
