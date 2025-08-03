package net.einself.folker.core.api;

public interface ActionMapper<D, I, O> {
    D toDomain(I request);

    O toResponse(D domain);
}
