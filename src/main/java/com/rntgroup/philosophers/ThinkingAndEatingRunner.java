package com.rntgroup.philosophers;

import com.rntgroup.philosophers.entity.Fork;
import com.rntgroup.philosophers.entity.Philosopher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ThinkingAndEatingRunner {
    public static void main(String[] args) {
        int NUMBERS_PHILOSOPHER = 5;

        try (ExecutorService executorService = Executors.newFixedThreadPool(NUMBERS_PHILOSOPHER)) {

            List<Fork> forks = new ArrayList<>();

            for (int i = 0; i < NUMBERS_PHILOSOPHER; i++) {
                forks.add(new Fork(new Semaphore(1)));
            }

            for (int i = 0; i < NUMBERS_PHILOSOPHER; i++) {
                Philosopher philosopher = new Philosopher(
                    forks.get(i),
                    forks.get((i + 1) % NUMBERS_PHILOSOPHER)
                );
                executorService.execute(philosopher);
            }
        }
    }
}
