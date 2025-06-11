package net.einself.folker.release.api;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record CreateReleaseRequest(String title, String albumArtist) {
}