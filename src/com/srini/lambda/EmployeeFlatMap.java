package com.srini.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFlatMap {

    private int empId;
    private String empName;
    private List<String> mobileNo;

    public static void main(String[] args) {
        EmployeeFlatMap e1 = new EmployeeFlatMap(101,  "Anil", Arrays.asList("9382327231","9237226362"));
        EmployeeFlatMap e2 = new EmployeeFlatMap(102,  "Smith", Arrays.asList("8232324231","8823726362"));
        EmployeeFlatMap e3 = new EmployeeFlatMap(103,  "Rohit", Arrays.asList("9382323723","8232324231"));
        EmployeeFlatMap e4 = new EmployeeFlatMap(104,  "Kohli", Arrays.asList("8823726362","8232324231"));

        List<EmployeeFlatMap> empList = new ArrayList();
        empList.add(e1);
        empList.add(e2);
        empList.add(e3);
        empList.add(e4);

        List<Integer> integerStream = empList.stream().map(e -> e.getEmpId()).collect(Collectors.toList());
        System.out.println(integerStream);
        List<String> mobileList = empList.stream().flatMap
                (e -> e.getMobileNo().stream().distinct()).collect(Collectors.toList());


        List<String> empNameList = empList.stream().map(EmployeeFlatMap::getEmpName).collect(Collectors.toList());
        System.out.println(empNameList);
        List<String> empMobileList = empList.stream().flatMap(e -> e.getMobileNo().stream()).distinct().collect(Collectors.toList());
        System.out.println(empMobileList);
    }

    public EmployeeFlatMap(int empId, String empName, List<String> mobileNo) {
        super();
        this.empId = empId;
        this.empName = empName;
        this.mobileNo = mobileNo;
    }



    public int getEmpId() {
        return empId;
    }



    public void setEmpId(int empId) {
        this.empId = empId;
    }



    public String getEmpName() {
        return empName;
    }



    public void setEmpName(String empName) {
        this.empName = empName;
    }



    public List<String> getMobileNo() {
        return mobileNo;
    }



    public void setMobileNo(List<String> mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "EmployeeFlatMap [empId=" + empId + ", empName=" + empName + ", mobileNo=" + mobileNo + "]";
    }


}