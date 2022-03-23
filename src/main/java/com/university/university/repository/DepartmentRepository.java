package com.university.university.repository;

import com.university.university.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByNameIgnoreCase(String name);
}
