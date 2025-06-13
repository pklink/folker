package net.einself.folker.release.domain;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record ReleaseCreatedEvent(Release release) {
}
