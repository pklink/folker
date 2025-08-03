package net.einself.folker.release.api.action;

import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record SearchReleasesResponse(
        UUID id,
        String title,
        String albumArtist
) {
}
