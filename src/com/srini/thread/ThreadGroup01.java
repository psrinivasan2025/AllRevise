package com.srini.thread;

public class ThreadGroup01 {

    public static void main(String args[]){
/* System.out.println(Thread.currentThread().getName());
Thread t = new Thread(() -> System.out.println(Thread.currentThread().getName()));
t.start();*/


        ThreadGroup threadGroup = new ThreadGroup("Thread Group");
        Thread t1 = new Thread(threadGroup, () -> System.out.println("Thread - 1 "+Thread.currentThread().getName()));
        Thread t2 = new Thread(threadGroup, () -> System.out.println("Thread - 2 "+Thread.currentThread().getName()));
        Thread t3 = new Thread(threadGroup, () -> System.out.println("Thread - 3 "+Thread.currentThread().getName()));

        Runnable r1 = () -> {
            System.out.println("hello world!!");
        };

        new Thread(r1).start();
        new Thread(() -> System.out.println("hello world")).start();
    }
}
