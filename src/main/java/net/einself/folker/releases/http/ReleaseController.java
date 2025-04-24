package net.einself.folker.releases.http;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.exceptions.HttpStatusException;
import jakarta.validation.Valid;
import net.einself.folker.releases.domain.Release;
import net.einself.folker.releases.domain.ReleaseService;
import org.bson.types.ObjectId;

import static io.micronaut.http.HttpStatus.NOT_FOUND;

@Controller("/releases")
public class ReleaseController {

    private final ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @Post
    public HttpResponse<Release> create(@Body @Valid CreateReleaseRequest request) {
        Release release = new Release(
                request.title(),
                request.albumArtist(),
                request.medium()
        );

        Release savedRelease = releaseService.save(release);
        return HttpResponse.created(savedRelease);
    }

    @Get
    public ListReleaseResponse getAllReleases() {
        return new ListReleaseResponse(releaseService.findAll());
    }

    @Get("/{id}")
    public Release getReleaseById(@PathVariable ObjectId id) {
        return releaseService.findById(id)
                .orElseThrow(() -> new HttpStatusException(NOT_FOUND, "Release not found"));
    }
}