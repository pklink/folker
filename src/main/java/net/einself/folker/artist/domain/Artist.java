package net.einself.folker.artist.domain;

import org.jmolecules.ddd.annotation.AggregateRoot;

import java.util.UUID;

@AggregateRoot
public class Artist {

    private final UUID id;
    private String name;

    public Artist(UUID id, String name) {
        this.id = id;
        setName(name);
    }

    public Artist(String name) {
        this(UUID.randomUUID(), name);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
