package com.xrhy.hermes.util;

public interface Action<T> {
    void call(T o);
}