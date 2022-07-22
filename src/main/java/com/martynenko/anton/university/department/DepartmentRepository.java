package com.martynenko.anton.university.department;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * {@link Department} repository CRUD contract.
 * @author Martynenko Anton
 * @since 1.0
 */

public interface DepartmentRepository extends JpaRepository<Department, Long> {
  /**
   * Find single entity by it's name.
   * @param name {@link Department} name to found
   * @return {@link Department} object or null
   */
  Department findByNameIgnoreCase(String name);
}
