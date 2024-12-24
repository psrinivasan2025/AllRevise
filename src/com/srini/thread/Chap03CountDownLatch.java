package com.srini.thread;

import java.util.concurrent.CountDownLatch;

public class Chap03CountDownLatch {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(4);
        Worker first = new Worker(countDownLatch, 4000, "Worker - 1");
        Worker second = new Worker(countDownLatch, 300, "Worker - 2");
        Worker third = new Worker(countDownLatch, 1000, "Worker - 3");
        Worker fourth = new Worker(countDownLatch, 500, "Worker - 4");

        first.start();
        second.start();
        third.start();
        fourth.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" .. finished !! ");
    }

}

class Worker extends Thread{

    private int delay;
    private CountDownLatch latch;

    public Worker(CountDownLatch latch, int delay, String name){
        super(name);
        this.delay = delay;
        this.latch = latch;
    }
    @Override
    public void run(){

        System.out.println("Entering thread .." +getName());
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        latch.countDown();
        System.out.println("Exiting thread .." +getName()+" .. Releasing latch ..");
    }
}
