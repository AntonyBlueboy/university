package com.martynenko.anton.university.employee;

import com.martynenko.anton.university.UniversityApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UniversityApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
class DatabaseEmployeeServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private DatabaseEmployeeService databaseEmployeeService;

  @Test
  void contextLoads(){
    assertThat(databaseEmployeeService).isNotNull();
  }

  @Test
  void getEmployees() {
    String subName = "Some";
    String subNameNotFound = "Somenf";
    List<Employee> employees = new ArrayList<>();
    employees.add(Mockito.mock(Employee.class));

    when(employeeRepository.findAllByNameContainingIgnoreCase(subName)).thenReturn(employees);
    when(employeeRepository.findAllByNameContainingIgnoreCase(subNameNotFound)).thenReturn(Collections.emptyList());

    assertThat(databaseEmployeeService.getEmployees(subName)).isEqualTo(employees);
    assertThat(databaseEmployeeService.getEmployees(subNameNotFound)).isEmpty();
  }
}