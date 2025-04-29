package net.einself.folker.release.query;

import io.micronaut.nats.annotation.NatsListener;
import io.micronaut.nats.annotation.Subject;
import net.einself.folker.release.domain.entity.Release;
import net.einself.folker.release.domain.event.ReleaseCreatedEvent;

import java.util.ArrayList;
import java.util.List;

@NatsListener
public class ReleaseQueryHandler {

    private final List<Release> releases = new ArrayList<>();

    @Subject("release.events")
    public void handleEvent(ReleaseCreatedEvent event) {
        Release release = new Release(event.releaseId(), event.title());
        releases.add(release);
    }

}
