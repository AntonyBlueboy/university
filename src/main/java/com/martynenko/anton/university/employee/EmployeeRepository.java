package com.martynenko.anton.university.employee;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * {@link Employee} repository CRUD contract.
 * @author Martynenko Anton
 * @since 1.0
 */

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  /**
   * Find all employees with subname .
   * @param subName subname of employee
   * @return {@link Employee} list or empty list
   */
  @NotNull List<Employee> findAllByNameContainingIgnoreCase(@NotNull String subName);
}
