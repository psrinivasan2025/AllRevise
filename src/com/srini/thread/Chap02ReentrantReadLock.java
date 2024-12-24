package com.srini.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Chap02ReentrantReadLock {


    public static void main(String[] args) {
        List<Integer> numList = new ArrayList<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.readLock();
        Thread reader1 = new Thread(new MyReader(numList, readLock));
        Thread reader2 = new Thread(new MyReader(numList, readLock));
        Thread reader3 = new Thread(new MyReader(numList, readLock));
        Thread writer1 = new Thread(new MyWriter(numList, writeLock));
        Thread writer2 = new Thread(new MyWriter(numList, writeLock));
        reader1.start();
        reader2.start();
        reader3.start();
        writer1.start();
        writer2.start();
    }

}

class MyReader implements Runnable {

    List<Integer> numList;
    Lock readLock;
    MyReader(List<Integer> numList, Lock readLock){
        this.numList = numList;
        this.readLock = readLock;
    }
    public void run() {

        while(true){
            readData();
        }
    }
    private void readData() {
        readLock.lock();
        System.out.println(numList);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            readLock.unlock();
        }
    }
}

class MyWriter implements Runnable {

    List<Integer> numList;
    Lock writeLock;
    MyWriter(List<Integer> numList, Lock writeLock){
        this.numList = numList;
        this.writeLock = writeLock;
    }

    public void run() {
        while(true){
            writeData();
        }
    }

    private void writeData() {
        Random random = new Random();
        writeLock.lock();
        numList.add(random.nextInt(10));
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        finally{
            writeLock.unlock();
        }
    }
}


