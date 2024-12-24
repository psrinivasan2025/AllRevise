package com.srini.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class FutureVsCompletableFuture {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

/*Future<String> future =  Executors.newFixedThreadPool(2).submit(()->{

System.out.println(Thread.currentThread().getName());
try {
TimeUnit.SECONDS.sleep(2);
System.out.println("Hello world !!!");

} catch (InterruptedException e) {

e.printStackTrace();
}
return "Hello World";

});

while(!future.isDone())
System.out.println("Waiting for wake up ...");*/
        System.out.println("Welcome back !! ");
        CompletableFuture<String> completableFuture = new CompletableFuture<String>();
        Executors.newFixedThreadPool(2).submit(()->{

            System.out.println(Thread.currentThread().getName());
/* try {
TimeUnit.SECONDS.sleep(2);
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}*/
            completableFuture.complete("Hello World");

        });
        while(!completableFuture.isDone())
            System.out.println("Waiting for thread to complete ... ");
        System.out.println(completableFuture.get());
        System.out.println("!! Welcome back !!!!! ");
    }

}
