
package com.srini.thread;

import java.util.ArrayList;
import java.util.List;

public class Chap02SynchronizedBasicRaceCondition {

    public static int globalVariable = 0;

    public static Object object = new Object();
    public static void main(String[] args) {

// ThreadGroup tg = new ThreadGroup("ThreadGroup");
        List<Thread> listThread = new ArrayList<>();
        for(int i =0; i< 50; i++){
// System.out.println("Hello "+globalVariable);
            Thread t1 = new InnerThread();
            t1.start();
            listThread.add(t1);
        }

        listThread.forEach( t  -> {
            try {
                t.join();
            } catch (InterruptedException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
        });


        System.out.println("Global variable:"+globalVariable);

    }


    static class InnerThread extends Thread{

        public void run(){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            synchronized (object) {
                globalVariable++;
            }

        }
    }

}