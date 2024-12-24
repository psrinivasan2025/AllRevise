package com.srini.async;

import java.util.concurrent.CompletableFuture;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class OddorEvenCompletable {

    private static Object obj = new Object();

    static IntPredicate evenPredicate = n -> n%2 ==0;
    static IntPredicate oddPredicate = n -> n%2 !=0;

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.runAsync(() -> OddorEvenCompletable.execute(evenPredicate));
        CompletableFuture.runAsync(() -> OddorEvenCompletable.execute(oddPredicate));
        Thread.sleep(1000);
    }

    public static void execute(IntPredicate predicate){
        IntStream.range(1, 10).filter(predicate).forEach(OddorEvenCompletable::printNumber);
    }

    public static void printNumber(int n){
        synchronized (obj) {

            try {
                System.out.println(Thread.currentThread().getName()+" "+n);
                obj.notify();
                obj.wait();

            } catch (InterruptedException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}
