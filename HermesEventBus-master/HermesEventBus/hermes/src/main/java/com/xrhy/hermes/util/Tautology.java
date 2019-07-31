package com.xrhy.hermes.util;

/**
 * @auther ps on 2019/7/31.
 */
public class Tautology<T> implements Condition<T>{

    @Override
    public boolean satisfy(T o) {
        return true;
    }
}
