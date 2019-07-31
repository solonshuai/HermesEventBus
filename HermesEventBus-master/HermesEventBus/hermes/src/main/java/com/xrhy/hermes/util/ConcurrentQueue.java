package com.xrhy.hermes.util;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @auther ps on 2019/7/31.
 */
public class ConcurrentQueue <T> {

    private final ConcurrentLinkedQueue<T> queue;

    public ConcurrentQueue() {
        queue = new ConcurrentLinkedQueue<T>();
    }

    public T poll() {
        synchronized (this) {
            return queue.poll();
        }
    }

    public boolean offerIfNotEmpty(T e) {
        synchronized (this) {
            return !queue.isEmpty() && queue.offer(e);
        }
    }

    public boolean offer(T e) {
        synchronized (this) {
            return queue.offer(e);
        }
    }
}
