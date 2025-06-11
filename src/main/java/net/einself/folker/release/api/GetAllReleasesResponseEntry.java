package net.einself.folker.release.api;

import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record GetAllReleasesResponseEntry(
        UUID id,
        String title,
        String albumArtist,
        String medium
) {
}
