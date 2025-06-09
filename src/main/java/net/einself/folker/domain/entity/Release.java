package net.einself.folker.domain.entity;


import io.micronaut.core.util.StringUtils;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.util.UUID;

@AggregateRoot
public class Release {

    @Identity
    private final UUID id;
    private String title;
    private String albumArtist;

    public Release(UUID id, String title, String albumArtist) {
        this.id = id;
        setTitle(title);
        setAlbumArtist(albumArtist);
    }

    public Release(String title, String albumArtist) {
        this(UUID.randomUUID(), albumArtist, title);
    }

    public UUID getId() {
        return id;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        if (StringUtils.isEmpty(albumArtist)) {
            throw new IllegalArgumentException("albumArtist cannot be empty");
        }

        this.albumArtist = albumArtist;
    }

    public String getTitle() {
        if (StringUtils.isEmpty(title)) {
            throw new IllegalArgumentException("title cannot be empty");
        }

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
