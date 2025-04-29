package net.einself.folker.release.command;

import jakarta.inject.Singleton;
import net.einself.folker.release.domain.command.CreateReleaseCommand;
import net.einself.folker.release.domain.event.ReleaseCreatedEvent;
import net.einself.folker.release.infrastructure.eventing.EventPublisher;
import reactor.core.publisher.Mono;

@Singleton
public class ReleaseCommandHandler {

    private final EventPublisher eventPublisher;

    public ReleaseCommandHandler(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public Mono<Void> handle(CreateReleaseCommand command) {
        ReleaseCreatedEvent event = new ReleaseCreatedEvent(command.releaseId(), command.title());
        return eventPublisher.publish(event);
    }

}
