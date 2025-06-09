package net.einself.folker.api.request;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CreateArtistRequest(String name) {
}
