package net.einself.folker.release.api.action;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Singleton;
import net.einself.folker.core.api.Action;
import net.einself.folker.release.application.ReleaseQueryBus;
import net.einself.folker.release.application.query.SearchReleasesQuery;
import net.einself.folker.release.domain.Release;

import java.util.List;

@Singleton
public class SearchReleasesAction implements Action<SearchReleasesRequest, Page<SearchReleasesResponse>> {

    private final ReleaseQueryBus releaseQueryBus;
    private final SearchReleasesActionMapper searchReleasesActionMapper;

    public SearchReleasesAction(ReleaseQueryBus releaseQueryBus, SearchReleasesActionMapper searchReleasesActionMapper) {
        this.releaseQueryBus = releaseQueryBus;
        this.searchReleasesActionMapper = searchReleasesActionMapper;
    }

    @Override
    public HttpResponse<Page<SearchReleasesResponse>> run(SearchReleasesRequest request) {
        List<Release> releases = releaseQueryBus.query(new SearchReleasesQuery());
        List<SearchReleasesResponse> list = releases.stream()
                .map(searchReleasesActionMapper::toResponse)
                .toList();

        Page<SearchReleasesResponse> page = Page.of(list, Pageable.UNPAGED, (long) list.size());
        return HttpResponse.ok(page);
    }
}
