package com.xrhy.hermes.util;

/**
 * @auther ps on 2019/7/31.
 */
public class IdenticalFunction <T> implements Function<T, T> {
    public T call(T o) {
        return o;
    }
}
