package com.srini.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentLambda {

    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(new Student("Sachin","Java",78),new Student("Sourav","J2EE",76),new Student("Steven","Spring",93),
                new Student("Virat","Docker",23),new Student("Tendulkar","J2EE",71),new Student("Gangully","Spring MVC",70),
                new Student("Jason","Java",12),new Student("Smith","Docker",73),new Student("Lokesh","Spring",98),
                new Student("Holder","Java",33),new Student("Anil","Kubernetes",66),new Student("Gill","Spring",92));

        System.out.println(studentList.stream().collect(Collectors.groupingBy(Student::getCourseName, Collectors.counting())));
        System.out.println(studentList.stream().collect(Collectors.groupingBy(Student::getCourseName, Collectors.maxBy(Comparator.comparingInt(Student::getMarks)))));

        System.out.println(studentList.stream().collect(Collectors.groupingBy(Student::getCourseName,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Student::getMarks)), s -> s.get().getStudentName()))));

        System.out.println(studentList.stream().collect(Collectors.groupingBy(Student::getCourseName,Collectors.mapping(Student::getStudentName, Collectors.toList()))));
    }

}

class Student{
    private String studentName;
    private String courseName;
    private int marks;
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public int getMarks() {
        return marks;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }
    public Student(String studentName, String courseName, int marks) {
        super();
        this.studentName = studentName;
        this.courseName = courseName;
        this.marks = marks;
    }
    @Override
    public String toString() {
        return " [ " + studentName + ", " + courseName + ", " + marks + "]";
    }


}
