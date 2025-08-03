package net.einself.folker.release.application.command;

import jakarta.inject.Singleton;
import net.einself.folker.core.application.EventPublisher;
import net.einself.folker.core.application.command.CommandHandler;
import net.einself.folker.core.application.command.CommandResult;
import net.einself.folker.release.domain.Release;
import net.einself.folker.release.domain.ReleaseCreatedEvent;
import net.einself.folker.release.domain.ReleaseRepository;

@Singleton
public class CreateReleaseCommandHandler implements CommandHandler<CreateReleaseCommand, Release> {

    private final ReleaseRepository releaseRepository;
    private final EventPublisher eventPublisher;

    public CreateReleaseCommandHandler(ReleaseRepository releaseRepository, EventPublisher eventPublisher) {
        this.releaseRepository = releaseRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public CommandResult<Release> handle(CreateReleaseCommand command) {
        Release release = releaseRepository.save(command.release());

        ReleaseCreatedEvent event = new ReleaseCreatedEvent(release);
        eventPublisher.publish(event);

        return CommandResult.ok(release);
    }

}
