package com.common.array;


import com.common.help.Zhou_StdRandom;

/**
 * @author zhou
 * time:2018-04-02
 */
public class ToArray {
    private static final java.util.concurrent.locks.Lock lock = new java.util.concurrent.locks.ReentrantLock();
    private static final java.util.concurrent.locks.Condition condition = lock.newCondition();
    private static final java.util.concurrent.atomic.AtomicInteger atomicInteger = new java.util.concurrent.atomic.AtomicInteger(10);
    private volatile static ToArray toArray = null;
    private final int START = 1;
    private final int END = 100;
    private Integer start;
    private Integer end;
    private Integer size = 0;
    private ToArray() {

    }

    public static int[] toFinalArray() {
        int[] arr = new int[]{41, 10, 32, 16, 16, 3, 26, 88, 89, 60, 57, 43, 38, 85, 56, 96, 84};
        return arr;
    }

    public static ToArray getToArray() {
        try {
            lock.lock();
            atomicInteger.getAndIncrement();//  以原子方式将当前值加 1。但是返回的是未更新的值
            if (toArray == null) toArray = new ToArray();
            Thread.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//锁释放!
        }
        return toArray;
    }

    public int[] toIntArray() {
        int num = Zhou_StdRandom.uniform(12, 20);

        int[] arr = null;
        if (size == 0) arr = new int[num];

        synchronized (this) {

            if (start == null) start = START;
            if (end == null) end = END;
            if (start > end && start != null && end != null) {
                int result = start;
                start = end;
                end = result;
            }

            for (int i = 0; i < num; i++) {
                if (arr == null) arr = new int[size];
                if (start < end) {
                    arr[i] = Zhou_StdRandom.uniform(this.start, this.end);
                } else {
                    throw new IllegalArgumentException("argument must be positive");
                }
            }
        }
        return arr;
    }

    public ToArray setStart(Integer start) {
        synchronized (this) {
            this.start = start;
        }
        return this;
    }

    public ToArray setEnd(Integer end) {
        synchronized (this) {
            this.end = end;
        }
        return this;
    }

    public ToArray setSize(Integer size) {
        synchronized (this) {
            this.size = size;
        }
        return this;
    }
}

