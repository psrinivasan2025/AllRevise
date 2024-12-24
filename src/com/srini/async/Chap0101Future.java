package com.srini.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Chap0101Future {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService exService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        System.out.println(Runtime.getRuntime().availableProcessors());

        Future<String> future1 = exService.submit(() -> {
            System.out.println("Executing Task 1 ..");
            Thread.sleep(5000);

            return "Task  1 Completed ..";
        });

        Future<String> future2 = exService.submit(() -> {
            System.out.println("Executing Task 2 ..");
            Thread.sleep(2000);
            return "Task  2 Completed ..";
        });

        System.out.println("In main after submitting tasks ...");
        System.out.println(future1.get());
        System.out.println(future2.get());

        System.out.println("End of main");
    }



}
