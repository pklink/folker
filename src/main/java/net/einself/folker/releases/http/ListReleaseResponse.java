package net.einself.folker.releases.http;

import io.micronaut.serde.annotation.Serdeable;
import net.einself.folker.releases.domain.Release;

import java.util.List;

@Serdeable
public record ListReleaseResponse(List<Release> items) {
}