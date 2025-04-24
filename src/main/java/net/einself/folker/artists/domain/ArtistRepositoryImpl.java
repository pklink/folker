package net.einself.folker.artists.domain;

import jakarta.inject.Singleton;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

@Singleton
class ArtistRepositoryImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistRepositoryImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist save(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public Optional<Artist> findById(ObjectId id) {
        return artistRepository.findById(id);
    }

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }
}
