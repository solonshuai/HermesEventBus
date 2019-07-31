package com.xrhy.hermes.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther ps on 2019/7/31.
 */
public class ObjectCanary2 <T> {

    private volatile T object;

    private final AtomicInteger pendingTaskNumber;

    private final Lock lock;

    private final Condition condition;

    private final ExecutorService executor;

    public ObjectCanary2() {
        object = null;
        pendingTaskNumber = new AtomicInteger(0);
        lock = new ReentrantLock();
        condition = lock.newCondition();
        executor = Executors.newSingleThreadExecutor();
    }

    public void action(final Action<? super T> action) {
        if (object == null || pendingTaskNumber.get() > 0) {
            // The following statement must be executed before the runnable is put into the queue.
            // TODO Will the following statements be reordered at the compile time?
            pendingTaskNumber.incrementAndGet();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    if (object == null) {
                        try {
                            lock.lock();
                            while (object == null) {
                                condition.await();
                            }
                            action.call(object);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            lock.unlock();
                        }
                    } else {
                        action.call(object);
                    }
                    // The following statement must be executed after the action is performed.
                    pendingTaskNumber.decrementAndGet();
                }
            });
        } else {
            action.call(object);
        }
    }

    public <R> R calculate(final Function<? super T, ? extends R> function) {
        if (object == null || pendingTaskNumber.get() > 0) {
            pendingTaskNumber.incrementAndGet();
            Future<R> future = executor.submit(new Callable<R>() {
                @Override
                public R call() throws Exception {
                    R result = null;
                    if (object == null) {
                        try {
                            lock.lock();
                            while (object == null) {
                                condition.await();
                            }
                            result = function.call(object);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            lock.unlock();
                        }
                    } else {
                        result = function.call(object);
                    }
                    pendingTaskNumber.decrementAndGet();
                    return result;
                }
            });
            try {
                return future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return function.call(object);
        }
    }

    public void set(T object) {
        if (object == null) {
            throw new IllegalArgumentException("You cannot assign null to this object.");
        }
        lock.lock();
        this.object = object;
        condition.signalAll();
        lock.unlock();
    }

}