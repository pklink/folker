package com.example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.validation.Valid;

@Controller("/releases")
public class ReleaseController {

    private final ReleaseRepository releaseRepository;

    public ReleaseController(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    @Post
    public HttpResponse<Release> create(@Body @Valid CreateReleaseRequest request) {
        Release release = new Release(
            request.title(),
            request.albumArtist(),
            request.medium()
        );
        
        Release savedRelease = releaseRepository.save(release);
        return HttpResponse.created(savedRelease);
    }
}