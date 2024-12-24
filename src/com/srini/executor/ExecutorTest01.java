package com.srini.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest01 {

    public static void main(String[] args) {

        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService exService = Executors.newFixedThreadPool(threads);

        Runnable r = () ->{
            for(int i = 0; i< 3; i++)
                System.out.println(Thread.currentThread().getName()+" "+i);
        };

        Runnable r1 = () -> {
            System.out.println((int)(Math.random()*100));
        };

        for(int i = 0; i< 10; i++){
            Thread t = new Thread(r1);
            System.out.println("From Thread "+i);
            exService.execute(t);
        }

    }

}

class MyRandomThread implements Runnable{

    @Override
    public void run() {

        int random = (int)(Math.random()*100);
        System.out.println(random);

    }

}