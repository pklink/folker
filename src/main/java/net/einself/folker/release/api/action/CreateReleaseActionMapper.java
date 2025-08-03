package net.einself.folker.release.api.action;

import jakarta.inject.Singleton;
import net.einself.folker.core.api.ActionMapper;
import net.einself.folker.release.domain.Release;

@Singleton
public class CreateReleaseActionMapper implements ActionMapper<Release, CreateReleaseRequest, CreateReleaseResponse> {

    @Override
    public Release toDomain(CreateReleaseRequest request) {
        return new Release(request.title(), request.albumArtist());
    }

    @Override
    public CreateReleaseResponse toResponse(Release release) {
        return new CreateReleaseResponse(
                release.getId(),
                release.getTitle(),
                release.getAlbumArtist()
        );
    }

}
