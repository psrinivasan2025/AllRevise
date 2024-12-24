package com.srini.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Chap0102CompletableFuture {

    private static ExecutorService exService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {

            System.out.println("In Completable Thread Task 001--> "+ Thread.currentThread().getName() );

        }, exService);


        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(()-> {
            System.out.println("In Completable Thread Task 002--> "+ Thread.currentThread().getName());
            return "Task2 done";
        }, exService);

        String result = completableFuture2.get();
        System.out.println("Main method completed .."+ result);

    }



    private static void executeWorkLoad(String name){
        System.out.println("Executing ...  "+name);
        sleep(5000);
    }

    private static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
