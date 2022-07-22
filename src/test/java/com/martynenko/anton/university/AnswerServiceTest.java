package com.martynenko.anton.university;

import com.martynenko.anton.university.exception.NoSuchDepartmentException;
import com.martynenko.anton.university.department.Department;
import com.martynenko.anton.university.employee.Employee;
import com.martynenko.anton.university.i18n.LocalizationHelper;
import com.martynenko.anton.university.ui.AnswerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.martynenko.anton.university.i18n.MessageCode.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UniversityApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
class AnswerServiceTest {

  @Mock
  private LocalizationHelper localizationHelper;

  @InjectMocks
  private AnswerService answerService;

  private static String answer = "answer";

  @Test
  void getGreetingAnswer() {
    when(localizationHelper.getMessage(ANSWER_COMMON_HI)).thenReturn(answer);

    assertThat(answerService.getGreetingAnswer()).isEqualTo(answer);
  }

  @Test
  void getExceptionAnswer() {
    String commonExeptionAnswer = "commonExeptionAnswer";
    String noSuchDepartmentExeptionAnswer = "noSuchDepartmentExeptionAnswer";

    when(localizationHelper.getMessage(ANSWER_DEPARTMENT_NO_SUCH)).thenReturn(noSuchDepartmentExeptionAnswer);
    when(localizationHelper.getMessage(ANSWER_COMMON_EXCEPTION)).thenReturn(commonExeptionAnswer);

    assertThat(answerService.getExceptionAnswer(new Exception())).isEqualTo(commonExeptionAnswer);
    assertThat(answerService.getExceptionAnswer(new NoSuchDepartmentException())).isEqualTo(noSuchDepartmentExeptionAnswer);
  }

  @Test
  void getAnythingElseAnswer() {
    when(localizationHelper.getMessage(ANSWER_COMMON_ELSE)).thenReturn(answer);

    assertThat(answerService.getAnythingElseAnswer()).isEqualTo(answer);
  }

  @Test
  void getGoodbyeAnswerIfExit() {
    String exitQuestion = "exit";
    String noExitQuestion = "something else";
    String byeAnswer = "exit";

    when(localizationHelper.getMessage(QUESTION_COMMON_EXIT)).thenReturn(exitQuestion);
    when(localizationHelper.getMessage(ANSWER_COMMON_BYE)).thenReturn(byeAnswer);

    assertThat(answerService.getGoodbyeAnswerIfExit(exitQuestion)).isEqualTo(byeAnswer);
    assertThat(answerService.getGoodbyeAnswerIfExit(noExitQuestion)).isNull();
  }

  @Test
  void getDepartmentsHeadNameAnswer() {
    String patternWithTwoVariables = "%s %s";
    String employeeName = "employeeName";
    String departmentName = "departmentName";
    String answerWithNames = String.format(patternWithTwoVariables, departmentName, employeeName);

    Employee employee = Mockito.mock(Employee.class);
    when(employee.getName()).thenReturn(employeeName);
    Department department = Mockito.mock(Department.class);
    when(department.getName()).thenReturn(departmentName);
    when(department.getHead()).thenReturn(employee);

    when(localizationHelper.getMessage(ANSWER_DEPARTMENT_HEAD_OF)).thenReturn(patternWithTwoVariables);

    assertThat(answerService.getDepartmentsHeadNameAnswer(department)).isEqualTo(answerWithNames);
  }

  @Test
  void getDepartmentEmployeeStatisticsAnswer() {

    String patternWithThreeDoubles = "%d %d %d";
    int assistantCount = 2;
    int assocProfCount = 4;
    int profCount = 1;

    String answerWithThreeDoubles = String.format(patternWithThreeDoubles, assistantCount, assocProfCount, profCount);

    List<Employee> employees = new ArrayList<>();

    for (int i = 0; i < 5; i++){
      if (i < assistantCount){
        Employee employee = Mockito.mock(Employee.class);
        when(employee.getDegree()).thenReturn(Employee.Degree.ASSISTANT);
        employees.add(employee);
      }
      if (i < assocProfCount){
        Employee employee = Mockito.mock(Employee.class);
        when(employee.getDegree()).thenReturn(Employee.Degree.ASSOCIATE_PROFESSOR);
        employees.add(employee);
      }
      if (i < profCount){
        Employee employee = Mockito.mock(Employee.class);
        when(employee.getDegree()).thenReturn(Employee.Degree.PROFESSOR);
        employees.add(employee);
      }
    }
    Department department = Mockito.mock(Department.class);
    when(department.getEmployees()).thenReturn(employees);

    when(localizationHelper.getMessage(ANSWER_DEPARTMENT_STATISTICS)).thenReturn(patternWithThreeDoubles);

    assertThat(answerService.getDepartmentEmployeeStatisticsAnswer(department)).isEqualTo(answerWithThreeDoubles);
  }

  @Test
  void getAvgSalaryStatisticsAnswer() {

    String patternWithTowVariables = "%s %.2f";
    String departmentName = "departmentName";
    Double avgSalary = 1000D;
    String answerWithTwoVariables = String.format(patternWithTowVariables, departmentName, avgSalary);

    Employee employee1 = Mockito.mock(Employee.class);
    when(employee1.getSalary()).thenReturn(500D);

    Employee employee2 = Mockito.mock(Employee.class);
    when(employee2.getSalary()).thenReturn(1500D);

    Department department = Mockito.mock(Department.class);
    when(department.getName()).thenReturn(departmentName);
    when(department.getEmployees()).thenReturn(Arrays.asList(employee1, employee2));

    when(localizationHelper.getMessage(ANSWER_DEPARTMENT_SALARY)).thenReturn(patternWithTowVariables);

    assertThat(answerService.getAvgSalaryStatisticsAnswer(department)).isEqualTo(answerWithTwoVariables);
  }

  @Test
  void getEmployeeCountAnswer() {
    int employeeCount = 10;
    List<Employee> employees = Stream.generate(Employee::new)
        .limit(employeeCount)
        .collect(Collectors.toList());

    Department department = Mockito.mock(Department.class);
    when(department.getEmployees()).thenReturn(employees);

    assertThat(answerService.getEmployeeCountAnswer(department)).isEqualTo(Integer.toString(employeeCount));
  }

  @Test
  void getGlobalEmployeeSearchAnswer() {
    String nothingFoundMessage = "nothingFoundMessage";

    when(localizationHelper.getMessage(ANSWER_EMPLOYEE_NOTHING_FOUND)).thenReturn(nothingFoundMessage);

    String name1 = "name1";
    String name2 = "name2";
    String returnString = String.format("%s, %s", name1, name2);

    Employee employee1 = Mockito.mock(Employee.class);
    when(employee1.getName()).thenReturn(name1);
    Employee employee2 = Mockito.mock(Employee.class);
    when(employee2.getName()).thenReturn(name2);
    List<Employee> employees = Arrays.asList(employee1, employee2);

    assertThat(answerService.getGlobalEmployeeSearchAnswer(Collections.EMPTY_LIST)).isEqualTo(nothingFoundMessage);
    assertThat(answerService.getGlobalEmployeeSearchAnswer(employees)).isEqualTo(returnString);
  }

  @Test
  void getIncorrectInputAnswer() {
    when(localizationHelper.getMessage(ANSWER_COMMON_INCORRECT_QUESTION)).thenReturn(answer);

    assertThat(answerService.getIncorrectInputAnswer()).isEqualTo(answer);
  }
}