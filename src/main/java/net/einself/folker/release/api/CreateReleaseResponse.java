package net.einself.folker.release.api;

import io.micronaut.serde.annotation.Serdeable;

import java.util.Set;
import java.util.UUID;

@Serdeable
public record CreateReleaseResponse(
        UUID id,
        String title,
        String albumArtist,
        Set<UUID> artists
) {
}