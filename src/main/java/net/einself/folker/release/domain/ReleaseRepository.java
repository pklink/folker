package net.einself.folker.release.domain;

import org.jmolecules.ddd.annotation.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReleaseRepository {

    List<Release> findAll();

    Optional<Release> findById(UUID id);

    Release save(Release release);

}
