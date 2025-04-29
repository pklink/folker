package net.einself.folker.release.api;

import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record CreateReleaseResponseDto(
        UUID id,
        String title
) {
}