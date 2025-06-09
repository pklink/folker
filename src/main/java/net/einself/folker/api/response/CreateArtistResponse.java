package net.einself.folker.api.response;

import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record CreateArtistResponse(UUID id, String name) {
}
