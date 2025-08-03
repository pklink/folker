package net.einself.folker.release.application.query;

import net.einself.folker.core.application.Query;
import net.einself.folker.release.domain.Release;

import java.util.Optional;
import java.util.UUID;

public record FindReleaseQuery(UUID id) implements Query<Optional<Release>> {
}
