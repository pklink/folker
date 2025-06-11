package net.einself.folker.artist.domain;

import org.jmolecules.ddd.annotation.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArtistRepository {

    List<Artist> findAll();

    Optional<Artist> findById(UUID id);

    Artist save(Artist release);

}
