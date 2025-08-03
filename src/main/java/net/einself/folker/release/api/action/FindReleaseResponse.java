package net.einself.folker.release.api.action;

import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record FindReleaseResponse(
        UUID id,
        String title,
        String albumArtist
) {
}
