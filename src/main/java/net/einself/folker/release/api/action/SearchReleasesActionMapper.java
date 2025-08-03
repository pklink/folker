package net.einself.folker.release.api.action;

import jakarta.inject.Singleton;
import net.einself.folker.core.api.ActionMapper;
import net.einself.folker.release.domain.Release;

@Singleton
public class SearchReleasesActionMapper implements ActionMapper<Release, SearchReleasesRequest, SearchReleasesResponse> {

    @Override
    public Release toDomain(SearchReleasesRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SearchReleasesResponse toResponse(Release domain) {
        return new SearchReleasesResponse(
                domain.getId(),
                domain.getTitle(),
                domain.getAlbumArtist()
        );
    }

}
