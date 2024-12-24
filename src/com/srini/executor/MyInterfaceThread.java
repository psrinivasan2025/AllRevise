package com.srini.executor;

public class MyInterfaceThread {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Test");
        }).start();
        MyInterface myInterface = () -> "Hello Spring";
        System.out.println(myInterface.sayHello());
        System.out.println(myInterface.interfaceDefaultMethod());
        System.out.println(MyInterface.interfaceStaticMethod());
    }
}

@FunctionalInterface
interface MyInterface{
    String sayHello();
    static String interfaceStaticMethod(){
        return "Hello from Static";
    }
    default String interfaceDefaultMethod(){
        return "Hello from Default";
    }
}
