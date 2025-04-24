package net.einself.folker.artists.http;

import io.micronaut.serde.annotation.Serdeable;
import net.einself.folker.artists.domain.Artist;

import java.util.List;

@Serdeable
public record ListArtistResponse(List<Artist> items) {
}
