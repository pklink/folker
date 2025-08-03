package net.einself.folker.release.application.command;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import net.einself.folker.core.application.command.CommandHandler;
import net.einself.folker.core.application.command.CommandResult;
import net.einself.folker.release.application.ReleaseService;
import net.einself.folker.release.domain.Release;

@Singleton
public class CreateReleaseCommandHandler implements CommandHandler<CreateReleaseCommand, Release> {

    private final ReleaseService releaseService;

    @Inject
    public CreateReleaseCommandHandler(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @Override
    public CommandResult<Release> handle(CreateReleaseCommand command) {
        Release release = releaseService.create(command.release());
        return CommandResult.ok(release);
    }

}
