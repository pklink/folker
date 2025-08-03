package net.einself.folker.release.api.action;

import jakarta.inject.Singleton;
import net.einself.folker.core.api.ActionMapper;
import net.einself.folker.release.domain.Release;

@Singleton
public class FindReleaseActionMapper implements ActionMapper<Release, FindReleaseRequest, FindReleaseResponse> {

    @Override
    public Release toDomain(FindReleaseRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FindReleaseResponse toResponse(Release domain) {
        return new FindReleaseResponse(
                domain.getId(),
                domain.getTitle(),
                domain.getAlbumArtist()
        );
    }

}
