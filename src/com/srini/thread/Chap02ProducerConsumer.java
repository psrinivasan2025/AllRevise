package com.srini.thread;

import java.util.LinkedList;
import java.util.Queue;

public class Chap02ProducerConsumer {

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();

        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();

    }

    static class Producer implements Runnable{

        private Queue<String> queue;

        public Producer(Queue<String> queue){
            this.queue = queue;
        }

        @Override
        public void run() {

            while(true){
                try {
                    produceData();
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }

        }

        private void produceData() throws InterruptedException {

            synchronized (queue) {

                if(queue.size() == 10){
                    System.out.println("In producer, waiting ...");
                    queue.wait();
                }

                Thread.sleep(1000);

                System.out.println("Producing data with ID "+queue.size());
                queue.add("Element_"+queue.size());
                queue.notify();
            }

        }

    }

    static class Consumer implements Runnable{

        private Queue<String> queue;

        public Consumer(Queue<String> queue){
            this.queue = queue;
        }

        @Override
        public void run() {

            while(true){
                try {
                    consumeData();
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        }

        private void consumeData() throws InterruptedException {
            synchronized (queue) {
                if(queue.isEmpty())
                    System.out.println("Consumer is waiting ...");
                queue.wait();
            }
            Thread.sleep(700);
            System.out.println("Cosuming the data "+queue.remove());
            queue.notify();
        }

    }
}