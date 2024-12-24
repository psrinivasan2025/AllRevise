package com.srini.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class OddorEvenES {

    public static void main(String[] args){

        ExecutorService ex = Executors.newFixedThreadPool(2);
        IntStream.rangeClosed(1, 10).forEach(num -> {

            CompletableFuture<Integer> evenCompletableFuture = CompletableFuture.completedFuture(num)
                    .thenApplyAsync(x ->{
                        if(x%2 ==0){
                            System.out.println(Thread.currentThread().getName()+" ---> "+x);
                        }
                        return x;
                    }, ex);
            evenCompletableFuture.join();

            CompletableFuture<Integer> oddCompletableFuture = CompletableFuture.completedFuture(num)
                    .thenApplyAsync(x ->{
                        if(x%2 !=0){
                            System.out.println(Thread.currentThread().getName()+" "+x);
                        }
                        return x;
                    }, ex);
// oddCompletableFuture.join();
        });
        ex.shutdown();
    }


/*public static void main(String[] args) {

ExecutorService ex = Executors.newFixedThreadPool(2);

IntStream.rangeClosed(1, 10).forEach(num -> {

CompletableFuture.completedFuture(num)
.thenAcceptAsync(x -> {
if(x%2 ==0)
System.out.println(Thread.currentThread().getName()+" ---> "+x);
},ex);

CompletableFuture.completedFuture(num)
.thenAcceptAsync(x -> {
if(x%2 != 0)
System.out.println(Thread.currentThread().getName()+" "+x);
},ex);
});
}*/

}

