package com.srini.basic;

public class Fibonacci {

    public void printFactorial(int n){
        int sum = 1;

        for(int i = 1; i <= n; i++)
            sum *= i;
        System.out.println("Factorial : "+ sum);
    }
    public int recursiveFactorial(int n){
        if (n <= 1)
            return 1;
        else
            return n * recursiveFactorial(n-1);
    }

    public void printFibonacci(int n){
        int f1 = 0, f2 = 1, f3;
        System.out.print("Fibonacci numbers ..."+f1 + " \t "+ f2);
        for(int i = 1; i < n; i++){
            f3 = f1+f2;
            System.out.print(" \t "+ f3);
            f1 = f2;
            f2 = f3;
        }
    }

    public int recursiveFibonacci(int n){
        if(n <= 0)
            return 0;
        if(n == 1)
            return 1;
        else
            return recursiveFibonacci(n-1) + recursiveFibonacci(n-2);
    }
    public String reverseString(String str){
        //return new StringBuffer(str).reverse().toString();
        String temp = "";
        for(int i = str.length()-1; i >= 0; i--)
            temp += str.charAt(i);
        return temp;
    }
    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        fib.printFactorial(5);
        System.out.println("Recursive Factorial "+fib.recursiveFactorial(5));
        fib.printFibonacci(10);
        System.out.println();
        System.out.println(fib.recursiveFibonacci(5));
        System.out.println(fib.reverseString("hello"));
        System.out.println(fib.reverseString("Malayalam"));
        System.out.println("Is Palindrome "+ "mangognam".equals(fib.reverseString("mangognam")));
    }
}
