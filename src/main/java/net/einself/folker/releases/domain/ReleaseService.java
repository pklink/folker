package net.einself.folker.releases.domain;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface ReleaseService {
    /**
     * Save a release to the database
     *
     * @param release the release to save
     * @return the saved release with generated ID
     */
    Release save(Release release);

    /**
     * Find a release by its ID
     *
     * @param id the release ID
     * @return an Optional containing the release if found, or empty if not found
     */
    Optional<Release> findById(ObjectId id);

    /**
     * Find all releases
     *
     * @return a list of all releases
     */
    List<Release> findAll();
}
