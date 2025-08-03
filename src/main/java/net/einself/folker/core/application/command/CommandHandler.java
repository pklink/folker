package net.einself.folker.core.application.command;

@FunctionalInterface
public interface CommandHandler<C extends Command, R> {

    CommandResult<R> handle(C command);

}
