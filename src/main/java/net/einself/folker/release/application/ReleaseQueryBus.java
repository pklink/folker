package net.einself.folker.release.application;


import jakarta.inject.Singleton;
import net.einself.folker.core.application.Query;
import net.einself.folker.release.application.query.FindReleaseQuery;
import net.einself.folker.release.application.query.FindReleaseQueryHandler;
import net.einself.folker.release.application.query.SearchReleaseQuery;
import net.einself.folker.release.application.query.SearchReleaseQueryHandler;

@Singleton
public class ReleaseQueryBus {

    private final FindReleaseQueryHandler findReleaseQueryHandler;
    private final SearchReleaseQueryHandler searchReleaseQueryHandler;

    public ReleaseQueryBus(FindReleaseQueryHandler findReleaseQueryHandler, SearchReleaseQueryHandler searchReleaseQueryHandler) {
        this.findReleaseQueryHandler = findReleaseQueryHandler;
        this.searchReleaseQueryHandler = searchReleaseQueryHandler;
    }

    public <R> R query(Query<R> query) {
        if (query instanceof SearchReleaseQuery) {
            //noinspection unchecked
            return (R) searchReleaseQueryHandler.fetch((SearchReleaseQuery) query);
        }

        if (query instanceof FindReleaseQuery findReleaseQuery) {
            //noinspection unchecked
            return (R) findReleaseQueryHandler.fetch(findReleaseQuery);
        }

        throw new IllegalArgumentException("Unsupported query type: " + query.getClass());
    }

}
