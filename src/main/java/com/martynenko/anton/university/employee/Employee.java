package com.martynenko.anton.university.employee;

import com.martynenko.anton.university.department.Department;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity representing university employee .
 * @author Martynenko Anton
 * @since 1.0
 */

@ToString
@EqualsAndHashCode(exclude = "departments")
@Getter
@Entity
public class Employee {

  /**
   * {@link Employee} degree enumeration .
   * @author Martynenko Anton
   * @since 1.1
   */
  public enum Degree {

    /**
     * Assistant.
     */
    ASSISTANT,

    /**
     * Associate professor.
     */
    ASSOCIATE_PROFESSOR,

    /**
     * Professor.
     */
    PROFESSOR
  }

  /**
   * Simple numeric positive id.
   */
  @Getter(AccessLevel.NONE)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Employee string name .
   */
  private String name;

  /**
   * Salary double value .
   */
  private Double salary;

  /**
   * Degree of employee .
   */
  @Enumerated(EnumType.STRING)
  private Degree degree;

  /**
   * List of departments where employee working .
   */

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "department_employee",
      joinColumns = { @JoinColumn(name = "employee_id") },
      inverseJoinColumns = { @JoinColumn(name = "department_id") }
  )
  private List<Department> departments = new ArrayList<>();
}
