package net.einself.folker.domain.events;

import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record ReleaseCreatedEvent(
        UUID releaseId,
        String title
) {
}
