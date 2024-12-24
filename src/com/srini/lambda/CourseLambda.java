package com.srini.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CourseLambda {
    public static void main(String[] args) {
        List<Course> courseList = Arrays.asList(new Course("Spring Boot","Framework",23000,93),
                new Course("Spring MVC","Framework",313000,95),
                new Course("REST API","API",23000,89),
                new Course("Microservices","Microservices",32000,93),
                new Course("Fullstack","Framework",32000,91),
                new Course("AWS","Cloud",29000,92),
                new Course("Azure","Cloud",45000,94),
                new Course("Kubernetes","Cloud",29000,93),
                new Course("Docker","Cloud",93000,96));

        System.out.println(courseList.stream().allMatch(course -> course.getReviewScore()>90));
        System.out.println(courseList.stream().noneMatch(course -> course.getReviewScore()<90));
        System.out.println(courseList.stream().anyMatch(course -> course.getReviewScore()>90));
        System.out.println(courseList.stream().sorted(Comparator.comparingInt(Course::getNumberOfStudents))
                .collect(Collectors.toList()));
        System.out.println(courseList.stream().sorted(Comparator.comparingInt(Course::getNumberOfStudents)
                .thenComparing(Comparator.comparingInt(Course::getReviewScore))).collect(Collectors.toList()));
        System.out.println(courseList.stream().sorted(Comparator.comparingInt(Course::getNumberOfStudents)
                .reversed().thenComparing(Comparator.comparingInt(Course::getReviewScore))).collect(Collectors.toList()));
        System.out.println(courseList.stream().sorted(Comparator.comparingInt(Course::getReviewScore))
                .skip(2).limit(1).collect(Collectors.toList()));
        System.out.println(courseList.stream().takeWhile(c -> c.getReviewScore() > 90).collect(Collectors.toList()));
        System.out.println(courseList.stream().dropWhile(c -> c.getReviewScore() > 89).collect(Collectors.toList()));
        System.out.println(courseList.stream().max(Comparator.comparingInt(Course::getNumberOfStudents)).get());
        System.out.println(courseList.stream().min(Comparator.comparingInt(Course::getNumberOfStudents)).get());
        System.out.println(courseList.stream().sorted(Comparator.comparingInt(Course::getNumberOfStudents)
                .reversed()).findFirst().get());
        System.out.println(courseList.stream().sorted(Comparator.comparingInt(Course::getNumberOfStudents)
                ).findFirst().get());
        System.out.println(courseList.stream().filter(c -> c.getReviewScore() < 89)
                .findAny().orElse(new Course("Dummy Course","NA",00,0)));
        System.out.println(courseList.stream().filter(c -> c.getReviewScore() > 92)
                .mapToInt(Course::getNumberOfStudents).sum());
        System.out.println(courseList.stream().sorted(Comparator.comparingInt(Course::getReviewScore).reversed()
                ).findFirst().get().getNumberOfStudents());
        System.out.println(courseList.stream().collect(Collectors.groupingBy(
                course -> course.getCourseCategory(),Collectors.counting())));
        System.out.println(courseList.stream().collect(Collectors.groupingBy(
                course -> course.getCourseCategory(),
                Collectors.maxBy(Comparator.comparingInt(Course::getReviewScore)))));
        System.out.println(courseList.stream().collect(Collectors.groupingBy(Course::getCourseCategory,
                Collectors.mapping(Course::getCourseName,Collectors.toList()))));
        System.out.println(courseList.stream().collect(Collectors.groupingBy(Course::getCourseCategory,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Course::getReviewScore)),
                        course -> course.get().getCourseName()))));
    }

}

class Course {
    private String courseName;
    private String courseCategory;
    private int numberOfStudents;
    private int reviewScore;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public Course(String courseName, String courseCategory, int numberOfStudents, int reviewScore) {
        super();
        this.courseName = courseName;
        this.courseCategory = courseCategory;
        this.numberOfStudents = numberOfStudents;
        this.reviewScore = reviewScore;
    }

    public Course() {

    }

    @Override
    public String toString() {
        return " [" + courseName + ", "
                + numberOfStudents + ", " + reviewScore + "]";
    }
}

