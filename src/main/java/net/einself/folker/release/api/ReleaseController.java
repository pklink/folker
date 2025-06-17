package net.einself.folker.release.api;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import net.einself.folker.release.application.ReleaseMapper;
import net.einself.folker.release.application.ReleaseService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller("/releases")
public class ReleaseController {

    private final ReleaseMapper releaseMapper;
    private final ReleaseService releaseService;

    public ReleaseController(ReleaseMapper releaseMapper, ReleaseService releaseService) {
        this.releaseMapper = releaseMapper;
        this.releaseService = releaseService;
    }

    @Post
    public Mono<HttpResponse<CreateReleaseResponse>> create(@Body CreateReleaseRequest request) {
        return Mono.just(request)
                .map(releaseMapper::fromRequest)
                .map(releaseService::create)
                .map(releaseMapper::toCreateResponse)
                .map(HttpResponse::ok);
    }

    @Get
    public Mono<HttpResponse<Page<GetAllReleasesResponse>>> getAll() {
        return Flux.fromStream(releaseService.findAll().stream())
                .map(releaseMapper::toGetAllReleasesResponseEntry)
                .collectList()
                .map(items -> Page.of(items, Pageable.UNPAGED, (long) items.size()))
                .map(HttpResponse::ok);
    }

}