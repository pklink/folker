package net.einself.folker.release.domain;


import io.micronaut.core.util.StringUtils;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AggregateRoot
public class Release {

    @Identity
    private final UUID id;
    private String title;
    private String albumArtist;
    private Set<UUID> artists;

    public Release(UUID id, String title, String albumArtist, Set<UUID> artists) {
        this.id = id;
        setTitle(title);
        setAlbumArtist(albumArtist);
        setArtists(artists);
    }

    public Release(String title, String albumArtist, Set<UUID> artists) {
        this(UUID.randomUUID(), albumArtist, title, artists);
    }

    public Release(String title, String albumArtist) {
        this(UUID.randomUUID(), albumArtist, title, new HashSet<>());
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

    public Set<UUID> getArtists() {
        return artists;
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

    public void setArtists(Set<UUID> artists) {
        if (artists == null) {
            throw new IllegalArgumentException("artists cannot be null");
        }

        this.artists = artists;
    }
}
