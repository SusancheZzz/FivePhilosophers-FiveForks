package com.rntgroup.philosophers.entity;

import java.util.concurrent.TimeUnit;

public class Philosopher implements Runnable {

    private static int count = 1;
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(Fork leftFork, Fork rightFork) {
        this.id = count++;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                eat();
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        System.out.printf("Philosopher %s thinking...%n", id);
        TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
    }

    //Если id философа чётный -- берёт сначала(!) правую вилку, иначе -- левую
    private void eat() throws InterruptedException {
        if (this.id % 2 == 0) {
            rightFork.take();
            leftFork.take();
        } else {
            leftFork.take();
            rightFork.take();
        }

        System.out.printf("Philosopher %s eating...%n ", id);
        TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));

        rightFork.release();
        leftFork.release();
    }
}

