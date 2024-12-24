package com.srini.thread;

public class OddorEvenJava7 implements Runnable {

    Object obj;
    static int count = 1;


    public OddorEvenJava7(Object obj) {
        super();
        this.obj = obj;
    }

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Runnable r1 = new OddorEvenJava7(lock);
        Runnable r2 = new OddorEvenJava7(lock);
        new Thread(r1,"even").start();
        new Thread(r2,"odd").start();
        Thread.sleep(1000);
    }

    @Override
    public void run() {
        while (count < 10) {
            if (count % 2 == 0 && Thread.currentThread().getName().equalsIgnoreCase("even")) {

                synchronized (obj) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " " + count);
                        count++;
                        obj.notify();
                        obj.wait();

                    } catch (InterruptedException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
            if (count % 2 != 0 && Thread.currentThread().getName().equalsIgnoreCase("odd")) {

                synchronized (obj) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " " + count);
                        count++;
                        obj.notify();
                        obj.wait();

                    } catch (Exception e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }
        }
    }

}
