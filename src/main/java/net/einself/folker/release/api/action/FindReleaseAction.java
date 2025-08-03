package net.einself.folker.release.api.action;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Singleton;
import net.einself.folker.core.api.Action;
import net.einself.folker.release.application.ReleaseQueryBus;
import net.einself.folker.release.application.query.FindReleaseQuery;

import java.util.Optional;

@Singleton
public class FindReleaseAction implements Action<FindReleaseRequest, FindReleaseResponse> {

    private final ReleaseQueryBus queryBus;
    private final FindReleaseActionMapper actionMapper;

    public FindReleaseAction(ReleaseQueryBus queryBus, FindReleaseActionMapper actionMapper) {
        this.queryBus = queryBus;
        this.actionMapper = actionMapper;
    }

    @Override
    public HttpResponse<FindReleaseResponse> run(FindReleaseRequest request) {
        FindReleaseQuery query = new FindReleaseQuery(request.id());

        Optional<FindReleaseResponse> response = queryBus.query(query)
                .map(actionMapper::toResponse);

        if (response.isPresent()) {
            return HttpResponse.ok(response.get());
        } else {
            return HttpResponse.notFound();
        }
    }

}
