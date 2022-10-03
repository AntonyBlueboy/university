package com.martynenko.anton.university.employee;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Database realisation of {@link EmployeeService}.
 *
 * @author Martynenko Anton
 * @since 1.1
 */
@RequiredArgsConstructor
@Service
@Primary
public class DatabaseEmployeeService implements EmployeeService {

  /**
   * {@link Employee} repository.
   */

  private final EmployeeRepository employeeRepository;

  /**
   * Find all employees with subname .
   * @param subName subname of employee
   * @return {@link Employee} list or empty list
   */
  @Override
  @NotNull
  public List<Employee> getEmployees(@NotNull String subName) {
    return employeeRepository.findAllByNameContainingIgnoreCase(subName);
  }
}