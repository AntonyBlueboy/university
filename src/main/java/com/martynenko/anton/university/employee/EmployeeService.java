package com.martynenko.anton.university.employee;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * {@link Employee} service contract.
 * @author Martynenko Anton
 * @since 1.1
 */
public interface EmployeeService {

  /**
   * Find all employees with subname .
   * @param subName subname of employee
   * @return {@link Employee} list or empty list
   */
  @NotNull List<Employee> getEmployees(@NotNull String subName);
}
