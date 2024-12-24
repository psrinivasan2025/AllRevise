package com.srini.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chap03Deadlock {

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {


        Thread thread1 = new Thread(() -> {

            lock2.lock();
            System.out.println("Acquiring lock2");
            lock1.lock();
            System.out.println("Acquiring lock1");
            lock1.unlock();
            lock2.unlock();
        });

        Thread thread2 = new Thread(() -> {
            lock1.lock();
            System.out.println("Acquiring lock1");
            lock2.lock();
            System.out.println("Acquiring lock2");
            lock2.unlock();
            lock1.unlock();
        });

        thread1.start();
        thread2.start();
    }

}