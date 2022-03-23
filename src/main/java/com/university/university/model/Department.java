package com.university.university.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department  extends IdentifiedEntity{

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "head_id", referencedColumnName = "id")
    private Employee head;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "department_employee",
            joinColumns = { @JoinColumn(name = "department_id") },
            inverseJoinColumns = { @JoinColumn(name = "employee_id") }
    )
    private List<Employee> employees = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getHead() {
        return head;
    }

    public void setHead(Employee head) {
        this.head = head;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }



    @Override
    public String toString() {
        return "Department{" + super.toString() + '\'' +
                "name='" + name + '\'' +
                "head='" + head.getName() + '\'' +
                "employees count='" + employees.size() + '\'' +
                "} ";
    }
}
