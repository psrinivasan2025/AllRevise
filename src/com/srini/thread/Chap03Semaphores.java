package com.srini.thread;

import java.util.concurrent.Semaphore;

public class Chap03Semaphores {

    public static void main(String[] args) {

        Semaphore sem = new Semaphore(2, true);
        Thread mt1 = new Thread(new MyThread(sem, "A"));
        Thread mt2 = new Thread(new MyThread(sem, "B"));
        Thread mt3 = new Thread(new MyThread(sem, "C"));
        Thread mt4 = new Thread(new MyThread(sem, "D"));
        mt1.start();
        mt2.start();
        mt3.start();
        mt4.start();

        try {
            mt1.join();
            mt2.join();
            mt3.join();
            mt4.join();
        } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Final value "+Shared.count);
    }

}

class MyThread implements Runnable{

    Semaphore se;
    String name;

    public MyThread(Semaphore sem, String str) {
        se = sem;
        name = str;
    }

    @Override
    public void run() {

        if(name.equalsIgnoreCase("A")){
            System.out.println("Starting "+name);
            System.out.println("Waiting for a permit "+name);
            try {
                se.acquire();
                System.out.println("Gets a permit "+name);
                for(int i =0; i < 5; i++){
                    Shared.count++;
                    Thread.sleep(100);
                    System.out.println("Name "+name+"  "+Shared.count);
                }
            } catch (InterruptedException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Releaseing a permit "+name);
            se.release();
        }
        else if (name.equalsIgnoreCase("B")){
            System.out.println("Starting "+name);
            System.out.println("Waiting for a permit "+name);
            try {
                se.acquire();
                System.out.println("Gets a permit "+name);
                for(int i =0; i < 5; i++){
                    Shared.count += 2;
                    Thread.sleep(100);
                    System.out.println("Name "+name+"  "+Shared.count);
                }
            } catch (InterruptedException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Releaseing a permit "+name);
            se.release();
        }

        else if (name.equalsIgnoreCase("C")){
            System.out.println("Starting "+name);
            System.out.println("Waiting for a permit "+name);
            try {
                se.acquire();
                System.out.println("Gets a permit "+name);
                for(int i =0; i < 5; i++){
                    Shared.count--;
                    Thread.sleep(100);
                    System.out.println("Name "+name+"  "+Shared.count);
                }
            } catch (InterruptedException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Releaseing a permit "+name);
            se.release();
        }
        else if (name.equalsIgnoreCase("D")){
            System.out.println("Starting "+name);
            System.out.println("Waiting for a permit "+name);
            try {
                se.acquire();
                System.out.println("Gets a permit "+name);
                for(int i =0; i < 5; i++){
                    Shared.count--;
                    Thread.sleep(100);
                    System.out.println("Name "+name+"  "+Shared.count);
                }
            } catch (InterruptedException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Releaseing a permit "+name);
            se.release();
        }
    }

}

class Shared{
    static int count = 0;
}