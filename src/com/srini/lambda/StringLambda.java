package com.srini.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringLambda {
    public static void main(String[] args) {
        List<String> strList = Arrays.asList("AWS","Spring","Java", "AWS","Azure","Docker","GCP","Spring","Azure","Docker","GCP");
        System.out.println(strList.stream().collect(Collectors.groupingBy(str->str, Collectors.counting())));
        System.out.println(strList.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())));

        String str = "abdedsgesowe";
        System.out.println(Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(s->s,Collectors.counting())));
    }


}
