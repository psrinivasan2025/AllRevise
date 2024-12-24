package com.srini.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Chap04ThreadPoolExecutor {

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(3));

        threadPoolExecutor.prestartAllCoreThreads();

        System.out.println(threadPoolExecutor.getPoolSize()+" "+threadPoolExecutor.getActiveCount());

        threadPoolExecutor.execute(() -> {System.out.println("Task 1");});

        threadPoolExecutor.execute(() -> {System.out.println("Task 2");});

        threadPoolExecutor.execute(() -> {System.out.println("Task 3");});

        threadPoolExecutor.execute(() -> {System.out.println("Task 4");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        });

        threadPoolExecutor.execute(() -> {System.out.println("Task 5");});

        threadPoolExecutor.execute(() -> {System.out.println("Task 6");});


        System.out.println(threadPoolExecutor.getPoolSize()+" "+threadPoolExecutor.getActiveCount());

        threadPoolExecutor.execute(() -> {System.out.println("Task 7");});

        threadPoolExecutor.execute(() -> {System.out.println("Task 8");});

        threadPoolExecutor.execute(() -> {System.out.println("Task 9");});

        threadPoolExecutor.execute(() -> {System.out.println("Task 10");});

        threadPoolExecutor.execute(() -> {System.out.println("Task 11");});

        System.out.println(threadPoolExecutor.getPoolSize()+" "+threadPoolExecutor.getActiveCount());


/* while(true){
Future<?> future =  threadPoolExecutor.submit(new MyInteger(10));


if(future.isDone()){
System.out.println("Complted");
return;
}
// else
// System.out.println("Not yet");

try {
System.out.println(future.get());
} catch (ExecutionException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

threadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS);
}*/
    }
}

class MyInteger implements Runnable{

    int x;

    public MyInteger(int y){
        x = y;
    }

    @Override
    public void run() {
        x *= x;

    }

    public int getValue(){
        return x;
    }
}