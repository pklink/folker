package net.einself.folker.release.domain;

import org.junit.jupiter.api.Test;

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
    void setAlbumArtist() {
        Release underTest = new Release("foo-title", "foo-album-artist");
        underTest.setAlbumArtist("bar-album-artist");
        assertThat(underTest.getAlbumArtist()).isEqualTo("bar-album-artist");
    }

    @Test
    void setTitle() {
        Release underTest = new Release("foo-title", "foo-album-artist");
        underTest.setTitle("bar-title");
        assertThat(underTest.getTitle()).isEqualTo("bar-title");
    }
}