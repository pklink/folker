package net.einself.folker.domain.events;

import net.einself.folker.domain.entity.Artist;

public record ArtistCreatedEvent(Artist artist) {
}
