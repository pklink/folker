package net.einself.folker.release.application;


import jakarta.inject.Singleton;
import net.einself.folker.core.application.Query;
import net.einself.folker.release.application.query.SearchReleaseQueryHandler;
import net.einself.folker.release.application.query.SearchReleasesQuery;

@Singleton
public class ReleaseQueryBus {

    private final SearchReleaseQueryHandler searchReleaseQueryHandler;

    public ReleaseQueryBus(SearchReleaseQueryHandler searchReleaseQueryHandler) {
        this.searchReleaseQueryHandler = searchReleaseQueryHandler;
    }

    public <R> R query(Query<R> query) {
        if (query instanceof SearchReleasesQuery) {
            //noinspection unchecked
            return (R) searchReleaseQueryHandler.fetch((SearchReleasesQuery) query);
        }

        throw new IllegalArgumentException("Unsupported query type: " + query.getClass());
    }

}
