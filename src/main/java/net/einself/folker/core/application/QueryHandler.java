package net.einself.folker.core.application;

public interface QueryHandler<Q, R> {
    R fetch(Q query);
}
