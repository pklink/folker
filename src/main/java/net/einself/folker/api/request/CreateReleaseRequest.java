package net.einself.folker.api.request;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CreateReleaseRequest(String title, String albumArtist) {
}