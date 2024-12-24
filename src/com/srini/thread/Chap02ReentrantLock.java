package com.srini.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Chap02ReentrantLock {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Runnable w1 = new ReentrantWorker(lock, "Job1");
        Runnable w2 = new ReentrantWorker(lock, "Job2");
        Runnable w3 = new ReentrantWorker(lock, "Job3");
        Runnable w4 = new ReentrantWorker(lock, "Job4");

        pool.execute(w1);
        pool.execute(w2);
        pool.execute(w3);
        pool.execute(w4);

        pool.shutdown();
    }

}

class ReentrantWorker implements Runnable{

    private String name;
    private ReentrantLock re;

    public ReentrantWorker(ReentrantLock re, String name){
        this.re = re;
        this.name = name;
    }

    @Override
    public void run() {

        boolean isDone = false;
        Date d = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
        while(!isDone){

            boolean ans = re.tryLock();
            if(ans){
                try {
                    Thread.sleep(1000);
                    re.lock();

                    try{

                        System.out.println("Task "+name+" lock acquired at "+ft.format(d));
                        System.out.println("Task "+name+"Lock hold count .."+ re.getHoldCount());

                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    finally{
                        re.unlock();
                        System.out.println("Task "+name+" lock released at "+ft.format(d));
                    }
                }
                catch (InterruptedException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finally{
                    re.unlock();
                    System.out.println("Task "+name+" lock released at "+ft.format(d));
                }
                System.out.println("Task "+name+" Completed "+ft.format(d)+" --> Lock hold count .."+ re.getHoldCount());
                isDone = true;
            }
            else{
                System.out.println("Task "+name+" waiting for lock "+ft.format(d));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
// TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}