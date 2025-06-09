package net.einself.folker.application;

import jakarta.inject.Singleton;
import net.einself.folker.domain.entity.Artist;
import net.einself.folker.domain.repository.ArtistRepository;

import java.util.List;

@Singleton
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist create(Artist artist) {
        return artistRepository.save(artist);
    }

    public List<Artist> findAll() {
        return artistRepository.findAll();
    }
}
