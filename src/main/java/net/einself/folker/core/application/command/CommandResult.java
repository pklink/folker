package net.einself.folker.core.application.command;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

public class CommandResult<T> {

    private final boolean success;
    private final T value;
    private final List<Exception> exception;

    private CommandResult(boolean success, @Nullable T value, @Nullable List<Exception> exception) {
        this.success = success;
        this.value = value;
        this.exception = Optional.ofNullable(exception).orElse(List.of());
    }

    public CommandResult(boolean success, @NotNull T value) {
        this(success, value, List.of());
    }

    public CommandResult(boolean success, List<Exception> exception) {
        this(success, null, exception);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getValue() {
        return value;
    }

    public List<Exception> getExceptions() {
        return exception;
    }

    public static <T> CommandResult<T> ok(T value) {
        return new CommandResult<>(true, value);
    }

    public static <T> CommandResult<T> error(List<Exception> exceptions) {
        return new CommandResult<>(false, exceptions);
    }

}
