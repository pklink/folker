package net.einself.folker.artists.http;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import net.einself.folker.artists.domain.Artist;
import net.einself.folker.artists.domain.ArtistService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@MicronautTest
class ArtistControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    ArtistService artistService;

    @BeforeEach
    void setUp() {
        reset(artistService);
    }

    @Nested
    class CreateArtist {

        @Test
        void shouldCreateArtist_whenRequestIsValid() {
            // arrange
            CreateArtistRequest request = new CreateArtistRequest("Test Artist", "Test Biography", "Test Country");
            Artist savedArtist = new Artist(new ObjectId(), "Test Artist", "Test Biography", "Test Country");

            when(artistService.save(any(Artist.class))).thenReturn(savedArtist);

            // act
            HttpResponse<Artist> response = client.toBlocking().exchange(
                    HttpRequest.POST("/artists", request),
                    Artist.class
            );

            // assert
            assertEquals(HttpStatus.CREATED, response.status());
            assertNotNull(response.body());
            assertEquals("Test Artist", response.body().name());
            assertEquals("Test Biography", response.body().biography());
            assertEquals("Test Country", response.body().country());

            verify(artistService).save(any(Artist.class));
        }

        @Test
        void shouldReturn400BadRequest_whenRequestIsInvalid() {
            // arrange
            CreateArtistRequest request = new CreateArtistRequest("", null, null);

            // act
            HttpClientResponseException exception = assertThrows(
                    HttpClientResponseException.class,
                    () -> client.toBlocking().exchange(
                            HttpRequest.POST("/artists", request),
                            Artist.class
                    )
            );

            // assert
            assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        }
    }

    @Nested
    class GetAllArtists {

        @Test
        void shouldReturnAllArtists() {
            // arrange
            List<Artist> artists = List.of(
                    new Artist(new ObjectId(), "Artist 1", "Bio 1", "Country 1"),
                    new Artist(new ObjectId(), "Artist 2", "Bio 2", "Country 2")
            );

            when(artistService.findAll()).thenReturn(artists);

            // act
            HttpResponse<ListArtistResponse> response = client.toBlocking().exchange(
                    HttpRequest.GET("/artists"),
                    ListArtistResponse.class
            );

            // assert
            assertEquals(HttpStatus.OK, response.status());
            assertNotNull(response.body());
            assertEquals(2, response.body().items().size());
            assertEquals("Artist 1", response.body().items().get(0).name());
            assertEquals("Artist 2", response.body().items().get(1).name());

            verify(artistService).findAll();
        }
    }

    @Nested
    class GetArtistById {

        @Test
        void shouldReturnArtist_whenIdExists() {
            // arrange
            ObjectId id = new ObjectId();
            Artist artist = new Artist(id, "Test Artist", "Test Biography", "Test Country");

            when(artistService.findById(id)).thenReturn(Optional.of(artist));

            // act
            HttpResponse<Artist> response = client.toBlocking().exchange(
                    HttpRequest.GET("/artists/" + id),
                    Artist.class
            );

            // assert
            assertEquals(HttpStatus.OK, response.status());
            assertNotNull(response.body());
            assertEquals(id, response.body().id());
            assertEquals("Test Artist", response.body().name());

            verify(artistService).findById(id);
        }

        @Test
        void shouldReturn404NotFound_whenIdDoesNotExist() {
            // arrange
            ObjectId id = new ObjectId();

            when(artistService.findById(id)).thenReturn(Optional.empty());

            // act
            HttpClientResponseException exception = assertThrows(
                    HttpClientResponseException.class,
                    () -> client.toBlocking().exchange(
                            HttpRequest.GET("/artists/" + id),
                            Artist.class
                    )
            );

            // assert
            assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
            verify(artistService).findById(id);
        }
    }
}
