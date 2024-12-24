package com.srini.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Chap04ThreadPoolExecutorRejectedTasks {

    public static void main(String[] args) {


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2),
                (Runnable r, ThreadPoolExecutor executor) ->{
                    System.out.println("Task not completed ");
                });

        threadPoolExecutor.submit(new SleepingThread(1));
        threadPoolExecutor.submit(new SleepingThread(2));
        threadPoolExecutor.submit(new SleepingThread(3));

        System.out.println(threadPoolExecutor.getPoolSize());

        threadPoolExecutor.submit(new SleepingThread(4));
        threadPoolExecutor.submit(new SleepingThread(5));
        threadPoolExecutor.submit(new SleepingThread(6));

        System.out.println(threadPoolExecutor.getPoolSize());


        threadPoolExecutor.submit(new SleepingThread(7));

        System.out.println(threadPoolExecutor.getPoolSize());
    }

    static class SleepingThread implements Runnable{

        private final int id;
        public SleepingThread(int id){
            this.id = id;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(99999);
            } catch (InterruptedException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}