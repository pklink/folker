package net.einself.folker.core.infrastructure;

import io.micronaut.context.event.ApplicationEventPublisher;
import jakarta.inject.Singleton;
import net.einself.folker.core.application.EventPublisher;
import net.einself.folker.release.domain.ReleaseCreatedEvent;

@Singleton
public class MicronautApplicationEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher<ReleaseCreatedEvent> releaseCreatedEventPublisher;

    public MicronautApplicationEventPublisher(ApplicationEventPublisher<ReleaseCreatedEvent> releaseCreatedEventPublisher) {
        this.releaseCreatedEventPublisher = releaseCreatedEventPublisher;
    }

    @Override
    public void publish(ReleaseCreatedEvent event) {
        releaseCreatedEventPublisher.publishEvent(event);
    }

}
