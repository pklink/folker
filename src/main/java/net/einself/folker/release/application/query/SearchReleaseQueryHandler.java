package net.einself.folker.release.application.query;

import jakarta.inject.Singleton;
import net.einself.folker.core.application.QueryHandler;
import net.einself.folker.release.domain.Release;
import net.einself.folker.release.domain.ReleaseRepository;

import java.util.List;

@Singleton
public class SearchReleaseQueryHandler implements QueryHandler<SearchReleaseQuery, List<Release>> {

    private final ReleaseRepository releaseRepository;

    public SearchReleaseQueryHandler(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    @Override
    public List<Release> fetch(SearchReleaseQuery query) {
        return releaseRepository.findAll();
    }

}
