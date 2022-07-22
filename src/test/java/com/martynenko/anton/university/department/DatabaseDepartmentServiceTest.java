package com.martynenko.anton.university.department;

import com.martynenko.anton.university.UniversityApplication;
import com.martynenko.anton.university.exception.NoSuchDepartmentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UniversityApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
class DatabaseDepartmentServiceTest {

  @InjectMocks
  private DatabaseDepartmentService databaseDepartmentService;

  @Mock
  private DepartmentRepository departmentRepository;


  @Test
  void contextLoads() {
    assertThat(databaseDepartmentService).isNotNull();
  }

  @Test
  void getDepartmentByName() {

    String departmentName =  "Geography";
    Department department = new Department();

    String noSuchDepartment = "Dummy department";

    when(departmentRepository.findByNameIgnoreCase(departmentName)).thenReturn(department);
    when(departmentRepository.findByNameIgnoreCase(noSuchDepartment)).thenReturn(null);

    assertThat(databaseDepartmentService.getDepartmentByName(departmentName)).isEqualTo(department);

    assertThrows(NoSuchDepartmentException.class, () -> {
      databaseDepartmentService.getDepartmentByName(noSuchDepartment);
    });
  }
}
