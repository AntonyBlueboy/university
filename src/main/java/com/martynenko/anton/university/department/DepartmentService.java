package com.martynenko.anton.university.department;

import com.martynenko.anton.university.exception.NoSuchDepartmentException;
import org.jetbrains.annotations.NotNull;

/**
 * {@link Department} service contract.
 * @author Martynenko Anton
 * @since 1.1
 */

public interface DepartmentService {
  /**
   * Get single department by it's name.
   * @param departmentName name of department to get
   * @return {@link Department} entity
   * @throws NoSuchDepartmentException if no {@link Department} found
   */
  @NotNull Department getDepartmentByName(@NotNull String departmentName) throws NoSuchDepartmentException;
}
