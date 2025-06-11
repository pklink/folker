package net.einself.folker.artist.application;

import io.micronaut.context.event.ApplicationEventPublisher;
import jakarta.inject.Singleton;
import net.einself.folker.artist.domain.Artist;
import net.einself.folker.artist.domain.ArtistCreatedEvent;
import net.einself.folker.artist.domain.ArtistRepository;

import java.util.List;

@Singleton
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ApplicationEventPublisher<ArtistCreatedEvent> artistCreatedEventPublisher;

    public ArtistService(ArtistRepository artistRepository, ApplicationEventPublisher<ArtistCreatedEvent> artistCreatedEventPublisher) {
        this.artistRepository = artistRepository;
        this.artistCreatedEventPublisher = artistCreatedEventPublisher;
    }

    public Artist create(Artist artist) {
        Artist savedArtist = artistRepository.save(artist);
        artistCreatedEventPublisher.publishEvent(new ArtistCreatedEvent(artist));
        return savedArtist;
    }

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }
}
