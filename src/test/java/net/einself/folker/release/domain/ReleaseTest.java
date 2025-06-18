package net.einself.folker.release.domain;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ReleaseTest {

    @Test
    void getId() {
        Release underTest = new Release("foo-title", "foo-album-artist");
        assertThat(underTest.getId()).isNotNull();
    }

    @Test
    void getAlbumArtist() {
        Release underTest = new Release("foo-title", "foo-album-artist");
        assertThat(underTest.getAlbumArtist()).isEqualTo("foo-album-artist");
    }

    @Test
    void getTitle() {
        Release underTest = new Release("foo-title", "foo-album-artist");
        assertThat(underTest.getTitle()).isEqualTo("foo-title");
    }

    @Test
    void getArtists() {
        Artist artist = new Artist(UUID.randomUUID(), "foo-artist-name");
        Release underTest = new Release("foo-title", "foo-album-artist", Set.of(artist));

        assertThat(underTest.getArtists())
                .hasSize(1)
                .containsExactly(artist);
    }

    @Test
    void addArtist() {
        Release underTest = new Release("foo-title", "foo-album-artist");
        Artist artist = new Artist(UUID.randomUUID(), "foo-artist-name");
        underTest.addArtist(artist);

        assertThat(underTest.getArtists())
                .hasSize(1)
                .containsExactly(artist);
    }

    @Test
    void removeArtist() {
        Artist artist = new Artist(UUID.randomUUID(), "foo-artist-name");
        Release underTest = new Release("foo-title", "foo-album-artist", Set.of(artist));
        underTest.removeArtist(artist.artistId());
        assertThat(underTest.getArtists()).hasSize(0);
    }

    @Test
    void changeAlbumArtist() {
        Release underTest = new Release("foo-title", "foo-album-artist");
        underTest.changeAlbumArtist("bar-album-artist");
        assertThat(underTest.getAlbumArtist()).isEqualTo("bar-album-artist");
    }

    @Test
    void changeTitle() {
        Release underTest = new Release("foo-title", "foo-album-artist");
        underTest.changeTitle("bar-title");
        assertThat(underTest.getTitle()).isEqualTo("bar-title");
    }
}