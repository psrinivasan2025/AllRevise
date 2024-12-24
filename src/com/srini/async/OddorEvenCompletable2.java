package com.srini.async;

import java.util.concurrent.CompletableFuture;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class OddorEvenCompletable2 {

    private static Object object = new Object();
    private static IntPredicate evenPredicate = n -> n%2 ==0;
    private static IntPredicate oddPredicate = n -> n%2 !=0;

    public static void main(String[] args) throws InterruptedException  {

        CompletableFuture.runAsync(() -> OddorEvenCompletable2.execute());
        CompletableFuture.runAsync(() -> OddorEvenCompletable2.execute1(oddPredicate));
        CompletableFuture.runAsync(() -> OddorEvenCompletable2.execute1(evenPredicate));
        Thread.sleep(1000);
    }

    private static void execute1(IntPredicate predicate){
        IntStream.rangeClosed(1, 10).filter(predicate).forEach( n -> OddorEvenCompletable2.oddOrEven(n));
    }

    private static void oddOrEven(int n){

        synchronized (object) {
            try {
                System.out.println(Thread.currentThread().getName()+" "+n);
                object.notify();
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void execute (){
        System.out.println("Hello world");
        System.out.println(Thread.currentThread().getName()+" ");
    }
}
