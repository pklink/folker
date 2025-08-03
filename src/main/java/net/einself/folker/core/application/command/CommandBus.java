package net.einself.folker.core.application.command;

public interface CommandBus<R> {

    CommandResult<R> submit(Command command);

}
