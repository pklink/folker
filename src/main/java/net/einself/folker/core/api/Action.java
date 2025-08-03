package net.einself.folker.core.api;

import io.micronaut.http.HttpResponse;

public interface Action<I, O> {

    HttpResponse<O> run(I request);

}
