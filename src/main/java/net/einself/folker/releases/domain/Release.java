
package net.einself.folker.releases.domain;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

@Serdeable
@MappedEntity
public record Release(
        @Id
        @GeneratedValue
        ObjectId id,

        @NotBlank
        String title,

        @NotBlank
        String albumArtist,

        @NotNull
        Medium medium
) {
    public Release(String title, String albumArtist, Medium medium) {
        this(null, title, albumArtist, medium);
    }
}