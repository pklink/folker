package net.einself.folker.artist.domain;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArtistTest {

    @Test
    void addAlias() {
        Artist underTest = new Artist("foo-name");
        Artist alias = new Artist("foo-alias");
        underTest.addAlias(alias);
        assertThat(underTest.getAliases()).containsExactly(alias);
    }

    @Test
    void changeName() {
        Artist underTest = new Artist("foo-name");
        underTest.changeName("new-name");
        assertThat(underTest.getName()).isEqualTo("new-name");
    }

    @Test
    void changeName_invalidName() {
        Artist underTest = new Artist("foo-name");
        assertThatThrownBy(() -> underTest.changeName(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> underTest.changeName("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getAliases() {
        Artist alias = new Artist("foo-alias");
        Artist underTest = new Artist("foo-name", Set.of(alias));
        assertThat(underTest.getAliases()).containsExactly(alias);
    }

    @Test
    void getId() {
        Artist underTest = new Artist("foo-name");
        assertThat(underTest.getId()).isNotNull();
    }

    @Test
    void getName() {
        Artist underTest = new Artist("foo-name");
        assertThat(underTest.getName()).isEqualTo("foo-name");
    }

    @Test
    void removeAlias() {
        Artist alias = new Artist("foo-alias");
        Artist underTest = new Artist("foo-name", Set.of(alias));
        underTest.removeAlias(alias);
        assertThat(underTest.getAliases()).isEmpty();
    }

}
