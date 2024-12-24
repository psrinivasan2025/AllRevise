package com.srini.async;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenComposeCombine {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture<String> basePrep = CompletableFuture.supplyAsync(() -> {
// System.out.println("Base is ready !!");
            return "Base is ready ";
        });

        CompletableFuture<String> topPrep = CompletableFuture.supplyAsync(() -> {
// System.out.println("Topping are ready !!");
            return "Topping are ready ";
        });


        CompletableFuture<Integer> quantity = CompletableFuture.supplyAsync(() -> new Random().nextInt(10));
        CompletableFuture<String> billIt = quantity.thenComposeAsync(c -> {
            return CompletableFuture.supplyAsync(() -> c*20+" ");
        });

        CompletableFuture<String> rawPizza =  basePrep.thenCombineAsync(topPrep, (str1, str2) -> {
            return "Combining "+str1+str2;
        }).thenCombineAsync(quantity, (str, quan) -> {
            return "Preparing "+quan+" "+str;
        });

        CompletableFuture<String> pizza = rawPizza.thenComposeAsync(str -> {
            return CompletableFuture.supplyAsync(() -> "Preparation over Heat it --> "+str);
        }).thenComposeAsync(str -> {
            return CompletableFuture.supplyAsync(() -> "Final packing "+str);
        }).thenComposeAsync(str -> {
            return CompletableFuture.supplyAsync(() ->{
                String bill = "";
                try {
                    bill = billIt.get();
                } catch (InterruptedException | ExecutionException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return "Final bill is "+bill +"for Pizza " + str;
            });
        });

        System.out.println(rawPizza.get());
        System.out.println(pizza.get());
        System.out.println(billIt.get());
    }

}