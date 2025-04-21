package com.example;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Serdeable
@Introspected
public record CreateReleaseRequest(

        @NotBlank
        String title,

        @NotBlank
        String albumArtist,

        @NotNull
        Medium medium
) { }
