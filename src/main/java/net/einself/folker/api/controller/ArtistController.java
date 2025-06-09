package net.einself.folker.api.controller;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import net.einself.folker.api.request.CreateArtistRequest;
import net.einself.folker.api.response.CreateArtistResponse;
import net.einself.folker.api.response.GetAllArtistsResponseEntry;
import net.einself.folker.application.ArtistService;
import net.einself.folker.domain.entity.Artist;

import java.util.List;

@Controller("/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Post
    public HttpResponse<CreateArtistResponse> create(@Body CreateArtistRequest request) {
        Artist artist = new Artist(request.name());
        artistService.create(artist);
        CreateArtistResponse response = new CreateArtistResponse(artist.getId(), artist.getName());
        return HttpResponse.ok(response);
    }

    @Get
    public HttpResponse<Page<GetAllArtistsResponseEntry>> getAll() {
        List<GetAllArtistsResponseEntry> artists = artistService.findAll().stream()
                .map(artist -> new GetAllArtistsResponseEntry(artist.getId(), artist.getName()))
                .toList();
        return HttpResponse.ok(Page.of(artists, Pageable.UNPAGED, (long) artists.size()));
    }

}