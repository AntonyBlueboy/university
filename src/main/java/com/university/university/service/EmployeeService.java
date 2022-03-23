package com.university.university.service;

import com.sun.istack.NotNull;
import com.university.university.model.Department;
import com.university.university.model.Employee;
import com.university.university.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public @NotNull List<Employee> getEmployees(@NotNull String subName){
        return employeeRepository.findByNameContainingIgnoreCase(subName);
    }

    public Double countAvgSalary(Department department){
        return employeeRepository.countAvgSalaryByDepartmentName(department.getId());
    }
}
