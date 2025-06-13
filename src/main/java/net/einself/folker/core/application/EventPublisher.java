package net.einself.folker.core.application;

import net.einself.folker.release.domain.ReleaseCreatedEvent;

public interface EventPublisher {
    void publish(ReleaseCreatedEvent event);
}
