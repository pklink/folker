package net.einself.folker.core.application;

public interface QueryHandler<Q extends Query<R>, R> {
    R fetch(Q query);
}
