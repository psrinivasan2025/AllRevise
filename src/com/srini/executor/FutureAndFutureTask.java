package com.srini.executor;

import java.util.Random;
import java.util.concurrent.*;

public class FutureAndFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MySleepTime sleepTime1 = new MySleepTime(1000);
        MySleepTime sleepTime2 = new MySleepTime(1500);

        ExecutorService ex = Executors.newFixedThreadPool(2);
        FutureTask<String> futureTask1 = new FutureTask<>(sleepTime1,"Future Task1 completed");
        FutureTask<String> futureTask2 = new FutureTask<>(sleepTime2,"Future Task2 completed");
        FutureTask<String> futureTask3 = new FutureTask<>(()->"Future task3 completed");
        FutureTask<Integer> futureTask4 = new FutureTask<>(() -> {
            System.out.println("Future task4 completed");
            return new Random().nextInt(2500);
        });
        ex.submit(futureTask1);
        ex.submit(futureTask2);
        ex.submit(futureTask3);
        ex.submit(futureTask4);

        while (true){
            if(futureTask1.isDone() && futureTask2.isDone()){
                System.out.println("Both future1 & future2 are done !!");
                ex.shutdown();
                return;
            }
            if(!futureTask2.isDone())
                System.out.println("Future2 still running");
            System.out.println(futureTask2.get());
            if(futureTask3.isDone())
                System.out.println(futureTask3.get());
            if(futureTask4.isDone())
                System.out.println(futureTask4.get());
        }
    }
}

class MySleepTime implements Runnable{

    private final int sleepTime;

    public MySleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
class CallableThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        return new Random().nextInt(1000);
    }
}