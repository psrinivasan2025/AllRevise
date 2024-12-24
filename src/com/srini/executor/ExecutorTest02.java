package com.srini.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



public class ExecutorTest02 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {


        ExecutorService exService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Callable<Integer> randomInteger = () -> (int) (Math.random()*100);

        Callable<Employee> randomEmployee = new RandomEmployee();
        Future<Employee> future2 = exService.submit(randomEmployee);
        System.out.println(future2.get());

        future2 = exService.submit(randomEmployee);
        System.out.println(future2.get());

        future2 = exService.submit(randomEmployee);
        System.out.println(future2.get());

        Future<Integer> future1 = exService.submit(randomInteger);

        int x = future1.get();
        System.out.println(x);

    }

}

class RandomEmployee implements Callable<Employee>{

    List<Employee> employeeList = new ArrayList<Employee>();

    public RandomEmployee(){
        employeeList.add(new Employee(1, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(2, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(3, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(4, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(5, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(6, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(7, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(8, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(9, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(10, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
    }


    @Override
    public Employee call() throws Exception {

        Thread.sleep(2000);
        return employeeList.get((int) (Math.random()*10));

    }

}


class Employee
{
    int id;

    String name;

    int age;

    String gender;

    String department;

    int yearOfJoining;

    double salary;

    public Employee(int id, String name, int age, String gender, String department, int yearOfJoining, double salary)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.yearOfJoining = yearOfJoining;
        this.salary = salary;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public String getGender()
    {
        return gender;
    }

    public String getDepartment()
    {
        return department;
    }

    public int getYearOfJoining()
    {
        return yearOfJoining;
    }

    public double getSalary()
    {
        return salary;
    }

    @Override
    public String toString()
    {
        return "Id : "+id
                +", Name : "+name
                +", age : "+age
                +", Gender : "+gender
                +", Department : "+department
                +", Year Of Joining : "+yearOfJoining
                +", Salary : "+salary;
    }
}