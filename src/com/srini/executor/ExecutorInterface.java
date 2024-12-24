package com.srini.executor;

import java.util.concurrent.Executor;

public class ExecutorInterface {
    public static void main(String[] args) {
        MyExecutor executor = new MyExecutor();
        executor.execute(() ->{
            System.out.println("Hello from executor !!");
        });
    }
}

class MyExecutor implements Executor{


    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
