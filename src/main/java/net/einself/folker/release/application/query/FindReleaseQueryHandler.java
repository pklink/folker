package net.einself.folker.release.application.query;

import jakarta.inject.Singleton;
import net.einself.folker.release.domain.Release;
import net.einself.folker.release.domain.ReleaseRepository;

import java.util.Optional;

@Singleton
public class FindReleaseQueryHandler {

    private final ReleaseRepository releaseRepository;

    public FindReleaseQueryHandler(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    public Optional<Release> fetch(FindReleaseQuery query) {
        return releaseRepository.findById(query.id());
    }

}
