package com.srini.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Chap0204CompletableFuture {

    static List<NewEmployee> employeeList = new ArrayList<NewEmployee>();
    public static void main(String[] args) throws InterruptedException, ExecutionException {


        employeeList.add(new NewEmployee("111", "Jiya", "Brein", "zschwerin0@gmail.org", "Female", "TRUE", "TRUE", 5000,2));
        employeeList.add(new NewEmployee("112", "Anil", "Soad", "asaoda@gmail.org", "Male", "TRUE", "FALSE", 2000,4));
        employeeList.add(new NewEmployee("113", "Chris", "Hebert", "chebert@gmail.org", "Male", "TRUE", "TRUE", 9000,4));
        employeeList.add(new NewEmployee("114", "Rahul", "Dravid", "rdravid@gmail.org", "Male", "FALSE", "FALSE", 7000,3));
        employeeList.add(new NewEmployee("115", "Marianne", "Sing", "msing@gmail.org", "Male", "FALSE", "FALSE", 8000,2));
        employeeList.add(new NewEmployee("116", "Ravi", "Ashwin", "rashwin@gmail.org", "Female", "TRUE", "FALSE", 5000,3));
        employeeList.add(new NewEmployee("117", "Ravindra", "Jadeja", "rjadeja@gmail.org", "Male", "TRUE", "FALSE", 6000,2));
        employeeList.add(new NewEmployee("118", "Rinku", "Singh", "rsingh@gmail.org", "Male", "FALSE", "FALSE", 1000,1));
        employeeList.add(new NewEmployee("119", "James", "Anderson", "janderson@gmail.org", "Male", "TRUE", "TRUE", 3000,0));
        employeeList.add(new NewEmployee("120", "Christina", "Chein", "cchein@gmail.org", "Female", "TRUE", "TRUE", 4000,2));

        CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName());
// employeeList.stream().forEach(System.out::println);
        }).get();

        List<NewEmployee> newEmpList = CompletableFuture.supplyAsync(() ->{
            System.out.println(Thread.currentThread().getName());
            return employeeList.stream().filter(e -> e.getNewJoiner().equalsIgnoreCase("TRUE")).collect(Collectors.toList());
        }).get();
// newEmpList.stream().forEach(System.out::println);
        print();
        Thread.sleep(10);
    }

    static void print() throws InterruptedException, ExecutionException{

        ExecutorService ex = Executors.newFixedThreadPool(6);
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Fetch employee " +Thread.currentThread().getName());
            return employeeList;
        },ex).thenApplyAsync( (empList) -> {
            System.out.println("Filter newly joined employee " +Thread.currentThread().getName());
            return empList.stream().filter(e -> e.getNewJoiner().equalsIgnoreCase("TRUE")).collect(Collectors.toList());
        }).thenApplyAsync( (empList) -> {
            System.out.println("Filter newly joined employee with tranining pending " +Thread.currentThread().getName());
            return empList.stream().filter(e -> "FALSE".equalsIgnoreCase(e.getLearningPending())).collect(Collectors.toList());
        },ex).thenApplyAsync( (empList) -> {
            System.out.println("Getting all the email ids " +Thread.currentThread().getName());
            return empList.stream().map(e -> e.getEmail()).collect(Collectors.toList());
        },ex).thenAcceptAsync(emailIds -> {
            System.out.println("reminder email sent to " +Thread.currentThread().getName());
            emailIds.stream().forEach(System.out::println);
        },ex);


    }

}

class NewEmployee{

    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String newJoiner;
    private String learningPending;
    private int salary;
    private int rating;

    public NewEmployee(){

    }
    public NewEmployee(String employeeId, String firstName, String lastName, String email, String gender,
                       String newJoiner, String learningPending, int salary, int rating) {
        super();
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.newJoiner = newJoiner;
        this.learningPending = learningPending;
        this.salary = salary;
        this.rating = rating;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getNewJoiner() {
        return newJoiner;
    }
    public void setNewJoiner(String newJoiner) {
        this.newJoiner = newJoiner;
    }
    public String getLearningPending() {
        return learningPending;
    }
    public void setLearningPending(String learningPending) {
        this.learningPending = learningPending;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    @Override
    public String toString() {
        return "NewEmployee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", gender=" + gender + ", newJoiner=" + newJoiner + ", learningPending="
                + learningPending + ", salary=" + salary + ", rating=" + rating + "]";
    }

}
