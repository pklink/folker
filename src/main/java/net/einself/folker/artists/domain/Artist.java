package net.einself.folker.artists.domain;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;

@Serdeable
@MappedEntity
public record Artist(
        @Id
        @GeneratedValue
        ObjectId id,

        @NotBlank
        String name,

        String biography,

        String country
) {
    public Artist(String name, String biography, String country) {
        this(null, name, biography, country);
    }
}
