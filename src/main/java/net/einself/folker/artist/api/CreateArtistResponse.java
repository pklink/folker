package net.einself.folker.artist.api;

import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record CreateArtistResponse(UUID id, String name) {
}
