package net.einself.folker.artists.http;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable
@Introspected
public record CreateArtistRequest(

        @NotBlank
        String name,

        String biography,

        String country
) {
}
