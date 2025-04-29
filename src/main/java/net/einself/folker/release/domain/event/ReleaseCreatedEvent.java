package net.einself.folker.release.domain.event;

import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record ReleaseCreatedEvent(
        UUID releaseId,
        String title
) {
}
