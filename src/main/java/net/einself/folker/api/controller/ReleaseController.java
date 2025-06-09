package net.einself.folker.api.controller;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import net.einself.folker.api.request.CreateReleaseRequest;
import net.einself.folker.api.response.CreateReleaseResponse;
import net.einself.folker.api.response.GetAllReleasesResponseEntry;
import net.einself.folker.application.ReleaseService;
import net.einself.folker.domain.entity.Release;

import java.util.List;

@Controller("/releases")
public class ReleaseController {

    private final ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @Post
    public HttpResponse<CreateReleaseResponse> create(@Body CreateReleaseRequest request) {
        Release release = new Release(request.title(), request.albumArtist());
        releaseService.create(release);
        CreateReleaseResponse response = new CreateReleaseResponse(release.getId(), release.getTitle(), release.getAlbumArtist());
        return HttpResponse.ok(response);
    }

    @Get
    public HttpResponse<Page<GetAllReleasesResponseEntry>> getAll() {
        List<GetAllReleasesResponseEntry> releases = releaseService.findAll().stream()
                .map(release -> new GetAllReleasesResponseEntry(release.getId(), release.getTitle(), release.getAlbumArtist(), "VINYL"))
                .toList();

        Page<GetAllReleasesResponseEntry> page = Page.of(releases, Pageable.UNPAGED, (long) releases.size());
        return HttpResponse.ok(page);
    }

}