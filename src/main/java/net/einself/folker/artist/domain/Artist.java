package net.einself.folker.artist.domain;

import org.apache.commons.lang3.StringUtils;
import org.jmolecules.ddd.annotation.AggregateRoot;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AggregateRoot
public class Artist {

    private final UUID id;
    private String name;
    private Set<Artist> aliases;

    public Artist(UUID id, String name, Set<Artist> aliases) {
        this.id = id;
        setName(name);
        setAliases(aliases);
    }

    public Artist(String name, Set<Artist> aliases) {
        this(UUID.randomUUID(), name, aliases);
    }

    public Artist(String name) {
        this(UUID.randomUUID(), name, Set.of());
    }

    public void addAlias(Artist alias) {
        if (aliases == null) {
            throw new IllegalArgumentException("aliases cannot be null");
        }

        aliases.add(alias);
    }

    public void changeName(String name) {
        setName(name);
    }

    public Set<Artist> getAliases() {
        return new HashSet<>(aliases);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void removeAlias(Artist alias) {
        aliases.remove(alias);
    }

    private void setAliases(Set<Artist> aliases) {
        if (aliases == null) {
            throw new IllegalArgumentException("aliases must not be null");
        }

        this.aliases = new HashSet<>(aliases);
    }

    private void setName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("name must not be empty");
        }

        this.name = name;
    }
}
