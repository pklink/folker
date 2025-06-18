package net.einself.folker.release.domain;


import org.apache.commons.lang3.StringUtils;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.util.*;

@AggregateRoot
public class Release {

    @Identity
    private final UUID id;
    private final Set<Artist> artists;
    private String title;
    private String albumArtist;

    public Release(UUID id, String title, String albumArtist, Set<Artist> artists) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        if (artists == null) {
            throw new IllegalArgumentException("Artists cannot be null");
        }

        this.id = id;
        this.artists = new HashSet<>(artists);
        changeAlbumArtist(albumArtist);
        changeTitle(title);
    }

    public Release(String title, String albumArtist, Set<Artist> artists) {
        this(UUID.randomUUID(), title, albumArtist, artists);
    }

    public Release(String title, String albumArtist) {
        this(UUID.randomUUID(), title, albumArtist, new HashSet<>());
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

    public Set<Artist> getArtists() {
        return Collections.unmodifiableSet(artists);
    }

    public void addArtist(Artist artist) {
        if (artists == null) {
            throw new IllegalArgumentException("Artists cannot be null");
        }

        artists.add(artist);
    }

    public void removeArtist(UUID artistId) {
        artists.stream()
                .filter(artist -> Objects.equals(artist.artistId(), artistId))
                .findFirst()
                .ifPresent(artists::remove);
    }

    public void changeAlbumArtist(String albumArtist) {
        if (StringUtils.isEmpty(albumArtist)) {
            throw new IllegalArgumentException("albumArtist cannot be empty");
        }

        this.albumArtist = albumArtist;
    }

    public void changeTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            throw new IllegalArgumentException("title cannot be empty");
        }

        this.title = title;
    }
}
