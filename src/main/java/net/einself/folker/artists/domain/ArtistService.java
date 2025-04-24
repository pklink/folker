package net.einself.folker.artists.domain;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    /**
     * Save an artist to the database
     *
     * @param artist the artist to save
     * @return the saved artist with generated ID
     */
    Artist save(Artist artist);

    /**
     * Find an artist by its ID
     *
     * @param id the artist ID
     * @return an Optional containing the artist if found, or empty if not found
     */
    Optional<Artist> findById(ObjectId id);

    /**
     * Find all artists
     *
     * @return a list of all artists
     */
    List<Artist> findAll();
}
