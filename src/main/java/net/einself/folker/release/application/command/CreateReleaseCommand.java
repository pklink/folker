package net.einself.folker.release.application.command;

import net.einself.folker.core.application.command.Command;
import net.einself.folker.release.domain.Release;

public record CreateReleaseCommand(Release release) implements Command {

}
