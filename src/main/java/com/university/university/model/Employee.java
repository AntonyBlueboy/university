package com.university.university.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee extends IdentifiedEntity{
    public static final String ASSISTANT = "assistant";
    public static final String ASSOCIATE_PROFESSOR = "associate professor";
    public static final String PROFESSOR = "professor";

    private String name;
    private Double salary;
    private String degree;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "department_employee",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "department_id") }
    )
    private List<Department> departments = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Employee{" + super.toString() + '\'' +
                "name='" + name + '\'' +
                "salary='" + salary + '\'' +
                "degree='" + degree + '\'' +
                "departments count='" + departments.size() + '\'' +
                "} ";
    }
}
