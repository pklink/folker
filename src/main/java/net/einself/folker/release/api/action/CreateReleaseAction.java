package net.einself.folker.release.api.action;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Singleton;
import net.einself.folker.core.api.Action;
import net.einself.folker.core.application.command.CommandResult;
import net.einself.folker.release.application.command.CreateReleaseCommand;
import net.einself.folker.release.application.command.ReleaseCommandBus;
import net.einself.folker.release.domain.Release;

@Singleton
public class CreateReleaseAction implements Action<CreateReleaseRequest, CreateReleaseResponse> {

    private final CreateReleaseActionMapper mapper;
    private final ReleaseCommandBus commandBus;

    public CreateReleaseAction(CreateReleaseActionMapper mapper, ReleaseCommandBus commandBus) {
        this.mapper = mapper;
        this.commandBus = commandBus;
    }

    @Override
    public HttpResponse<CreateReleaseResponse> run(CreateReleaseRequest request) {
        Release release = mapper.toDomain(request);

        CreateReleaseCommand createCommand = new CreateReleaseCommand(release);
        CommandResult<Release> result = commandBus.submit(createCommand);

        if (result.isSuccess()) {
            CreateReleaseResponse response = mapper.toResponse(result.getValue());
            return HttpResponse.ok(response);
        } else {
            return HttpResponse.badRequest();
        }
    }


}
