package net.einself.folker.releases.http;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import net.einself.folker.releases.domain.Medium;
import net.einself.folker.releases.domain.Release;
import net.einself.folker.releases.domain.ReleaseService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MicronautTest
public class ReleaseControllerTest {

    @Inject
    private ReleaseService releaseService;

    @Inject
    @Client("/")
    HttpClient client;

    @MockBean(ReleaseService.class)
    ReleaseService releaseRepository() {
        return mock(ReleaseService.class);
    }

    @Nested
    class FindAll {

        @Test
        void getAllReleases_shouldReturnAllReleases() {
            // arrange
            Release release1 = new Release("Album 1", "Artist 1", Medium.CD);
            Release release2 = new Release("Album 2", "Artist 2", Medium.VINYL);
            List<Release> releases = List.of(release1, release2);

            when(releaseService.findAll()).thenReturn(releases);

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
            verify(releaseService).findAll();
        }

    }

    @Nested
    class FindOne {

        @Test
        void getReleaseById_shouldReturnRelease_whenIdExists() {
            // arrange
            String releaseIdStr = "507f1f77bcf86cd799439011";
            ObjectId releaseId = new ObjectId(releaseIdStr);
            Release release = new Release(releaseId, "Found Album", "Found Artist", Medium.CD);

            when(releaseService.findById(releaseId)).thenReturn(Optional.of(release));

            // act
            HttpResponse<Release> response = client.toBlocking()
                    .exchange(HttpRequest.GET("/releases/%s".formatted(releaseIdStr)), Release.class);

            // assert
            assertNotNull(response);
            assertEquals(HttpStatus.OK, response.status());

            assertNotNull(response.body());
            assertEquals(releaseId.toString(), response.body().id().toString());
            assertEquals("Found Album", response.body().title());
            assertEquals("Found Artist", response.body().albumArtist());
            assertEquals(Medium.CD, response.body().medium());
        }

        @Test
        void getReleaseById_shouldReturn404_whenIdDoesNotExist() {
            // arrange
            String nonExistentIdStr = "507f1f77bcf86cd799439012";
            ObjectId nonExistentId = new ObjectId(nonExistentIdStr);

            when(releaseService.findById(nonExistentId)).thenReturn(Optional.empty());

            // act & assert
            HttpRequest<?> request = HttpRequest.GET("/releases/" + nonExistentIdStr);
            HttpClientResponseException exception = assertThrows(
                    HttpClientResponseException.class,
                    () -> client.toBlocking().exchange(request, Release.class)
            );

            assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        }
    }

    @Nested
    class CreateOne {

        @Test
        void create_shouldCreateAndReturnRelease() {
            // arrange
            CreateReleaseRequest requestBody = new CreateReleaseRequest("New Album", "New Artist", Medium.CD);

            Release savedRelease = new Release(new ObjectId("68094bbdacdf712dc786c0a8"), "New Album", "New Artist", Medium.CD);
            when(releaseService.save(any(Release.class))).thenReturn(savedRelease);

            // act
            HttpRequest<?> request = HttpRequest.POST("/releases", requestBody);
            HttpResponse<Release> result = client.toBlocking().exchange(request, Release.class);

            // assert
            assertNotNull(result);
            assertEquals(HttpStatus.CREATED, result.status());

            assertNotNull(result.body());
            assertEquals("68094bbdacdf712dc786c0a8", result.body().id().toHexString());
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

}
