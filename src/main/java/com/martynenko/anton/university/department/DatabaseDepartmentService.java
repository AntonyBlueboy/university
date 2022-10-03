package com.martynenko.anton.university.department;

import com.martynenko.anton.university.exception.NoSuchDepartmentException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * Database realisation of {@link DepartmentService}.
 *
 * @author Martynenko Anton
 * @since 1.0
 */
@RequiredArgsConstructor
@Service
@Primary
public class DatabaseDepartmentService implements DepartmentService {

  /**
   * {@link Department} repository.
   */

  private final DepartmentRepository departmentRepository;

  /**
   * Get single department by it's name.
   * @param departmentName name of department to get
   * @return {@link Department} entity
   * @throws NoSuchDepartmentException if no {@link Department} found
   */
  @Override
  @NotNull
  public Department getDepartmentByName(@NotNull final String departmentName) throws NoSuchDepartmentException {
    return departmentRepository.findByNameIgnoreCase(departmentName).orElseThrow(NoSuchDepartmentException::new);
  }
}
