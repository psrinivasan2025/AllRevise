package com.srini.async;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenCompose {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture<String> completableFuture1 = CompletableFuture.completedFuture("Hello");

        CompletableFuture<String> completableFuture2 = CompletableFuture.completedFuture("World");

        System.out.println(completableFuture1.get()+" "+completableFuture2.get());

// completableFuture1.thenCompose(str -> str +" "+completableFuture2.get())
        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> "Hi ").thenApply(str -> str +" Lambda World !!");
        System.out.println(completableFuture3.get());

// CompletableFuture<CompletableFuture<String>> thenApply = getHelloMessage().thenApply(msg -> getHelloWorld(msg));
        CompletableFuture<String> completableFuture4 =  getHelloMessage().thenCompose(msg -> getHelloWorld(msg));
        System.out.println("Then Compose .."+completableFuture4.get());
    }

    public static CompletableFuture<String> getHelloMessage(){
        return CompletableFuture.completedFuture("Hello ");
    }

    public static CompletableFuture<String> getHelloWorld(String helloMsg){
        return CompletableFuture.supplyAsync(() -> helloMsg+ "  World !!! ");
    }

}
