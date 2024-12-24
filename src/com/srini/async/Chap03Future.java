package com.srini.async;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Chap03Future {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService exService = Executors.newCachedThreadPool();
        for(int i = 0; i< 10;i ++){
            exService.execute(new MyThread01());
        }

        List<Future> futureList = new ArrayList();
        for(int i =0; i<10; i++){
            Future<Integer> future = exService.submit(new MyThread02());
            futureList.add(future);
        }

        System.out.println("After calling future ... ");
        Iterator<Future> i = futureList.iterator();
        while(i.hasNext()){
            Future<Integer> future = i.next();
            System.out.println(future.get());
        }

        System.out.println("After retrieving from future ... ");
    }

}

class MyThread01 implements Runnable{

    @Override
    public void run() {

        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+" "+new Random().nextInt(100));
        } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

class MyThread02 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
// TODO Auto-generated method stub
        Thread.sleep(1000);
        return new Random().nextInt(100);
    }

}