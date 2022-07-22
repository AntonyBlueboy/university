package com.martynenko.anton.university.employee;

import com.martynenko.anton.university.department.Department;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing university employee .
 * @author Martynenko Anton
 * @since 1.0
 */

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

  /**
   * Getter for property 'name'.
   *
   * @return Value for property 'name'.
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for property 'degree'.
   *
   * @return Value for property 'degree'.
   */
  public Degree getDegree() {
    return degree;
  }

  /**
   * Getter for property 'salary'.
   *
   * @return Value for property 'salary'.
   */
  public Double getSalary() {
    return salary;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", salary=" + salary +
        ", degree=" + degree +
        ", departments size=" + departments.size() +
        '}';
  }
}
