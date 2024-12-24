package com.srini.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionalProgramming01 {

    public static void main(String[] args) {
        List<String> stLi = Arrays.asList("hello".split(""));
        List<Integer> numList = Arrays.asList(10,25,12,4,5,9,3);
        List<String> strList = Arrays.asList("Spring","Spring boot","Spring Core","Java","J2ee", "Microservices");
        numList.stream().filter(x -> x %2 ==0).map(x -> x*x).forEach(System.out::println);
        strList.stream().filter(x -> x.contains("Spring")).forEach(System.out::println);
        strList.stream().filter(x -> x.length() > 6).forEach(System.out::println);
        strList.stream().map(x -> x +" -->  "+x.length()).forEach(System.out::println);
        System.out.println("sum "+numList.stream().reduce(0,Integer::sum));
        System.out.println("Biggest "+numList.stream().reduce(Integer.MIN_VALUE, (x,y) -> x > y?x:y));
        System.out.println(numList.stream().filter(x -> x %2 ==0).map(x -> x*x).reduce(0,Integer::sum));

// numList.stream().distinct().forEach(System.out::println);
// numList.stream().sorted().forEach(System.out::println);
        List<String> sortedStrList = strList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        sortedStrList.forEach(System.out::println);
        strList.stream().sorted(Comparator.comparingInt(str -> str.length())).forEach(System.out::println);

        System.out.println(numList.stream().mapToInt(Integer::intValue).average().orElse(0));

    }

}