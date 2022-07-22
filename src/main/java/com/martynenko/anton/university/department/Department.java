package com.martynenko.anton.university.department;

import com.martynenko.anton.university.employee.Employee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing university department .
 * @author Martynenko Anton
 * @since 1.0
 */

@Entity
public class Department {

  /**
   * Simple numeric positive id.
   */

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Department's unique string name .
   */

  @Column(unique = true)
  private String name;

  /**
   * {@link Employee} entity representing the head of department .
   */

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "head_id", referencedColumnName = "id")
  private Employee head;

  /**
   * Department's {@link Employee}s list .
   */

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "department_employee",
      joinColumns = { @JoinColumn(name = "department_id") },
      inverseJoinColumns = { @JoinColumn(name = "employee_id") }
  )
  private List<Employee> employees = new ArrayList<>();

  /**
   * Getter for property 'name'.
   *
   * @return Value for property 'name'.
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for property 'head'.
   *
   * @return Value for property 'head'.
   */
  public Employee getHead() {
    return head;
  }

  /**
   * Getter for property 'employees'.
   *
   * @return Value for property 'employees'.
   */
  public List<Employee> getEmployees() {
    return employees;
  }

  @Override
  public String toString() {
    return "Department{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", head=" + head +
        ", employees count=" + employees.size() +
        '}';
  }
}
