package net.einself.folker.release.api;

import io.micronaut.data.model.Page;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import net.einself.folker.release.api.action.*;

import java.util.UUID;

@Controller
public class ReleaseController {

    private final CreateReleaseAction createReleaseAction;
    private final FindReleaseAction findReleaseAction;
    private final SearchReleasesAction searchReleasesAction;

    public ReleaseController(CreateReleaseAction createReleaseAction, FindReleaseAction findReleaseAction, SearchReleasesAction searchReleasesAction) {
        this.createReleaseAction = createReleaseAction;
        this.findReleaseAction = findReleaseAction;
        this.searchReleasesAction = searchReleasesAction;
    }

    @Post("/releases/create")
    public HttpResponse<CreateReleaseResponse> create(@Body CreateReleaseRequest request) {
        return createReleaseAction.run(request);
    }

    @Get("/releases")
    public HttpResponse<Page<SearchReleasesResponse>> getAll() {
        return searchReleasesAction.run(new SearchReleasesRequest());
    }

    @Get("/release/{id}")
    public HttpResponse<FindReleaseResponse> findRelease(@PathVariable UUID id) {
        return findReleaseAction.run(new FindReleaseRequest(id));
    }

}