package net.einself.folker.release.infrastructure.eventing;

import io.micronaut.nats.annotation.NatsClient;
import io.micronaut.nats.annotation.Subject;
import net.einself.folker.release.domain.event.ReleaseCreatedEvent;
import reactor.core.publisher.Mono;

@NatsClient
public interface EventPublisher {

    @Subject("release.events")
    Mono<Void> publish(ReleaseCreatedEvent event);

}
