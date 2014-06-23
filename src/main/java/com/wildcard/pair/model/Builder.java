package com.wildcard.pair.model;

/**
 * Interface for object builders.
 *
 * @param <T> the type of object that the builder constructs.
 */
public interface Builder<T> {
    public T build();
}
