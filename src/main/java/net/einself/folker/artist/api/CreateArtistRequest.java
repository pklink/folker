package net.einself.folker.artist.api;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CreateArtistRequest(String name) {
}
