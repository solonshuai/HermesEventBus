package com.xrhy.hermes.util;

public interface Condition<T> {
    boolean satisfy(T o);
}

