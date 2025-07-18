package com.mjc.stage2;

import java.util.Random;

public class ThreadSafeSingleton {
    // Write your code here!
    private static volatile ThreadSafeSingleton instance;

    private Random random;

    private ThreadSafeSingleton() {
        random = new Random();
    }

    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }

    public int getRandomNumber(int bound) {
        return random.nextInt(bound);
    }
}
