package net.einself.folker.release.api.action;

import io.micronaut.serde.annotation.Serdeable;

import java.util.Set;
import java.util.UUID;

@Serdeable
public record CreateReleaseRequest(String title, String albumArtist, Set<UUID> artists) {
}