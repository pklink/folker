package com.example.releases.http;

import com.example.releases.domain.Release;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record ListReleaseResponse(List<Release> items) {
}