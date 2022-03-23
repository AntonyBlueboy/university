package com.university.university.repository;

import com.sun.istack.NotNull;
import com.university.university.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContainingIgnoreCase(String subName);

    /**
     * Returns average salary per department.
     * this functionality could be implemented in java code. But it looks like, here is good case to use native sql
     * should be checked in case of datasource changing, but with H2 and MYSQL works great
     *
     * @param  departmentId  id of target department
     * @return      average salary by department in Double
     */

    @Query(
            value = "SELECT AVG(e.salary) AS AverageSalary FROM employee e " +
                    "JOIN department_employee de " +
                    "ON e.id = de.employee_id " +
                    "WHERE de.department_id = ?1 ;",
            nativeQuery = true)
    Double countAvgSalaryByDepartmentName(Long departmentId);
}
