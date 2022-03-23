package com.university.university.service;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import com.university.university.model.Department;
import com.university.university.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public @Nullable Department getDepartmentByName(@NotNull String departmentName){
        return departmentRepository.findByNameIgnoreCase(departmentName);
    }

}
