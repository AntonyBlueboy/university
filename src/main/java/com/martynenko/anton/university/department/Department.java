package com.martynenko.anton.university.department;

import com.martynenko.anton.university.employee.Employee;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing university department .
 * @author Martynenko Anton
 * @since 1.0
 */
@Getter
@EqualsAndHashCode(exclude = "employees")
@ToString(exclude = "employees")
@Entity
public class Department {

  /**
   * Simple numeric positive id.
   */

  @Getter(AccessLevel.NONE)
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

  @OneToOne()
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
}
