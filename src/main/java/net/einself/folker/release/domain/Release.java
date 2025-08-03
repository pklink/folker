package net.einself.folker.release.domain;


import org.apache.commons.lang3.StringUtils;
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
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        this.id = id;
        setAlbumArtist(albumArtist);
        setTitle(title);
    }

    public Release(String title, String albumArtist) {
        this(UUID.randomUUID(), title, albumArtist);
    }

    public UUID getId() {
        return id;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public String getTitle() {
        return title;
    }

    public void setAlbumArtist(String albumArtist) {
        if (StringUtils.isEmpty(albumArtist)) {
            throw new IllegalArgumentException("albumArtist cannot be empty");
        }

        this.albumArtist = albumArtist;
    }

    public void setTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            throw new IllegalArgumentException("title cannot be empty");
        }

        this.title = title;
    }
}
