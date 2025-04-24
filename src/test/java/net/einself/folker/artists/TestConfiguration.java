package net.einself.folker.artists;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Replaces;
import jakarta.inject.Singleton;
import net.einself.folker.artists.domain.ArtistService;

import static org.mockito.Mockito.mock;

@Factory
public class TestConfiguration {

    @Singleton
    @Replaces(ArtistService.class)
    public ArtistService artistService() {
        return mock(ArtistService.class);
    }
}
