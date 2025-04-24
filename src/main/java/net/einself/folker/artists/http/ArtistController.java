package net.einself.folker.artists.http;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.exceptions.HttpStatusException;
import jakarta.validation.Valid;
import net.einself.folker.artists.domain.Artist;
import net.einself.folker.artists.domain.ArtistService;
import org.bson.types.ObjectId;

import static io.micronaut.http.HttpStatus.NOT_FOUND;

@Controller("/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Post
    public HttpResponse<Artist> create(@Body @Valid CreateArtistRequest request) {
        Artist artist = new Artist(
                request.name(),
                request.biography(),
                request.country()
        );

        Artist savedArtist = artistService.save(artist);
        return HttpResponse.created(savedArtist);
    }

    @Get
    public ListArtistResponse getAllArtists() {
        return new ListArtistResponse(artistService.findAll());
    }

    @Get("/{id}")
    public Artist getArtistById(@PathVariable ObjectId id) {
        return artistService.findById(id)
                .orElseThrow(() -> new HttpStatusException(NOT_FOUND, "Artist not found"));
    }
}
