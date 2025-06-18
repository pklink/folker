package net.einself.folker.release.domain;

import org.apache.commons.lang3.StringUtils;
import org.jmolecules.ddd.annotation.ValueObject;

import java.util.UUID;

@ValueObject
public record Artist(
        UUID artistId,
        String name
) {

    public Artist {
        if (artistId == null) {
            throw new IllegalArgumentException("ArtistId cannot be null");
        }

        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
