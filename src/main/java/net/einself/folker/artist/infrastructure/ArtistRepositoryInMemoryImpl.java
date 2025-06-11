package net.einself.folker.artist.infrastructure;

import jakarta.inject.Singleton;
import net.einself.folker.artist.domain.Artist;
import net.einself.folker.artist.domain.ArtistRepository;
import org.jmolecules.architecture.layered.InfrastructureLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
@InfrastructureLayer
public class ArtistRepositoryInMemoryImpl implements ArtistRepository {

    private final List<Artist> artists = new ArrayList<>();

    @Override
    public List<Artist> findAll() {
        return artists;
    }

    @Override
    public Optional<Artist> findById(UUID id) {
        return artists.stream()
                .filter(release -> release.getId().equals(id))
                .findFirst();
    }

    @Override
    public Artist save(Artist release) {
        artists.add(release);
        return release;
    }

}
