package net.einself.folker.release.application;

import jakarta.inject.Singleton;
import net.einself.folker.release.api.CreateReleaseRequest;
import net.einself.folker.release.api.CreateReleaseResponse;
import net.einself.folker.release.api.GetAllReleasesResponse;
import net.einself.folker.release.domain.Release;

@Singleton
public class ReleaseMapper {

    public Release fromRequest(CreateReleaseRequest request) {
        return new Release(request.title(), request.albumArtist(), request.artists());
    }

    public CreateReleaseResponse toCreateResponse(Release release) {
        return new CreateReleaseResponse(
                release.getId(),
                release.getTitle(),
                release.getAlbumArtist(),
                release.getArtists()
        );
    }

    public GetAllReleasesResponse toGetAllReleasesResponseEntry(Release release) {
        return new GetAllReleasesResponse(
                release.getId(),
                release.getTitle(),
                release.getAlbumArtist(),
                release.getArtists()
        );
    }

}
