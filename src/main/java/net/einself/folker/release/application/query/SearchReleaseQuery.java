package net.einself.folker.release.application.query;

import net.einself.folker.core.application.Query;
import net.einself.folker.release.domain.Release;

import java.util.List;

public record SearchReleaseQuery() implements Query<List<Release>> {
}
