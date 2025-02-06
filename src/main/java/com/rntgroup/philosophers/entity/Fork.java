package com.rntgroup.philosophers.entity;

import java.util.concurrent.Semaphore;

public class Fork {

    private static int count = 1;
    private final int id;
    private final Semaphore semaphore;

    public Fork(Semaphore semaphore) {
        this.id = count++;
        this.semaphore = semaphore;
    }

    public void take() throws InterruptedException {
        semaphore.acquire();
    }

    public void release() {
        semaphore.release();
    }

    @Override
    public String toString() {
        return "Fork{" +
            "id=" + id +
            '}';
    }
}
