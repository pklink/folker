package net.einself.folker.release.api;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import net.einself.folker.release.command.ReleaseCommandHandler;
import net.einself.folker.release.domain.command.CreateReleaseCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller("/releases")
public class ReleaseController {

    private final ReleaseCommandHandler releaseCommandHandler;

    public ReleaseController(ReleaseCommandHandler releaseCommandHandler) {
        this.releaseCommandHandler = releaseCommandHandler;
    }


    @Post
    public Mono<HttpResponse<CreateReleaseResponseDto>> create(@Body CreateReleaseRequestDto request) {
        CreateReleaseCommand command = new CreateReleaseCommand(UUID.randomUUID(), request.title());
        return releaseCommandHandler.handle(command)
                .then(Mono.fromCallable(() -> new CreateReleaseResponseDto(command.releaseId(), command.title())))
                .map(HttpResponse::created);
    }


}