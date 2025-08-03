package net.einself.folker.release.application.query;

import jakarta.inject.Singleton;
import net.einself.folker.core.application.QueryHandler;
import net.einself.folker.release.domain.Release;
import net.einself.folker.release.domain.ReleaseRepository;

import java.util.Optional;

@Singleton
public class FindReleaseQueryHandler implements QueryHandler<FindReleaseQuery, Optional<Release>> {

    private final ReleaseRepository releaseRepository;

    public FindReleaseQueryHandler(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    @Override
    public Optional<Release> fetch(FindReleaseQuery query) {
        return releaseRepository.findById(query.id());
    }

}
