package com.srini.executor;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Chap04ScheduledThreadPoolExecutor {

    public static void main(String[] args) {

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
        scheduledThreadPoolExecutor.schedule(()-> System.out.println("About to launch a task"), 2, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(()-> { System.out.println(" Launching ... "); }, 3, 1, TimeUnit.SECONDS);

    }

}