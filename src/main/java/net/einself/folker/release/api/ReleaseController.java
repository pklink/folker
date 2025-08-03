package net.einself.folker.release.api;

import io.micronaut.data.model.Page;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import net.einself.folker.release.api.action.*;

@Controller
public class ReleaseController {

    private final CreateReleaseAction createReleaseAction;
    private final SearchReleasesAction searchReleasesAction;

    public ReleaseController(CreateReleaseAction createReleaseAction, SearchReleasesAction searchReleasesAction) {
        this.createReleaseAction = createReleaseAction;
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

}