package com.example.http;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record CollectionResponse<T>(List<T> items) {
}