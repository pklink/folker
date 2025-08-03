package net.einself.folker.release.application;

import jakarta.inject.Singleton;
import net.einself.folker.core.application.command.Command;
import net.einself.folker.core.application.command.CommandBus;
import net.einself.folker.core.application.command.CommandResult;
import net.einself.folker.release.application.command.CreateReleaseCommand;
import net.einself.folker.release.application.command.CreateReleaseCommandHandler;
import net.einself.folker.release.domain.Release;

@Singleton
public class ReleaseCommandBus implements CommandBus<Release> {

    private final CreateReleaseCommandHandler createReleaseCommandHandler;

    public ReleaseCommandBus(CreateReleaseCommandHandler createReleaseCommandHandler) {
        this.createReleaseCommandHandler = createReleaseCommandHandler;
    }

    @Override
    public CommandResult<Release> submit(Command command) {
        if (command instanceof CreateReleaseCommand createReleaseCommand) {
            return createReleaseCommandHandler.handle(createReleaseCommand);
        }

        throw new IllegalArgumentException("Unsupported command type");
    }

}
