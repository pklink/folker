package net.einself.folker.release.application.query;

import jakarta.inject.Singleton;
import net.einself.folker.release.domain.Release;
import net.einself.folker.release.domain.ReleaseRepository;

import java.util.List;

@Singleton
public class SearchReleaseQueryHandler {

    private final ReleaseRepository releaseRepository;

    public SearchReleaseQueryHandler(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    List<Release> fetch(SearchReleasesQuery query) {
        return releaseRepository.findAll();
    }

}
