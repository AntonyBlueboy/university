package com.martynenko.anton.university.ui;

import com.martynenko.anton.university.department.DepartmentService;
import com.martynenko.anton.university.employee.EmployeeService;
import com.martynenko.anton.university.exception.NoSuchDepartmentException;
import com.martynenko.anton.university.department.Department;
import com.martynenko.anton.university.employee.Employee;
import com.martynenko.anton.university.i18n.LocalizationHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import static com.martynenko.anton.university.i18n.MessageCode.*;

/**
 * Service to convert data to String responses for user interface.
 *
 * @author Martynenko Anton
 * @since 1.1
 */
@Service
public class AnswerService {

  /**
   * {@link LocalizationHelper} bean .
   */
  private final LocalizationHelper localizationHelper;

  /**
   * Bean serving {@link Department} entities   .
   */

  private final DepartmentService departmentService;

  /**
   * Bean serving {@link Employee} entities   .
   */

  private final EmployeeService employeeService;

  /**
   * Autowiring constructor.
   * @param localizationHelper {@link LocalizationHelper} bean
   * @param departmentService {@link DepartmentService} bean
   * @param employeeService {@link EmployeeService} bean
   */
  @Autowired
  public AnswerService(final LocalizationHelper localizationHelper,
                       final DepartmentService departmentService,
                       final EmployeeService employeeService) {
    this.localizationHelper = localizationHelper;
    this.departmentService = departmentService;
    this.employeeService = employeeService;
  }

  /**
   * Get the greetings message.
   * @return greeting message
   */
  @NotNull
  public String getGreetingAnswer() {
    return localizationHelper.getMessage(ANSWER_COMMON_HI);
  }

  /**
   * Get the error message according to the passed exception object .
   * @param  e exception
   * @return error message
   */
  @NotNull
  public String getExceptionAnswer(final Exception e) {
    if (e instanceof NoSuchDepartmentException) {
      return localizationHelper.getMessage(ANSWER_DEPARTMENT_NO_SUCH);
    } else {
      return localizationHelper.getMessage(ANSWER_COMMON_EXCEPTION);
    }
  }

  /**
   * Get the 'Anything else' message .
   * @return message
   */
  @NotNull
  public String getAnythingElseAnswer() {
    return localizationHelper.getMessage(ANSWER_COMMON_ELSE);
  }

  /**
   * Check the question for the string 'exit' and return the 'goodbye' message if it presents and null if doesn't .
   * @param question user's question
   * @return message
   */
  @Nullable
  public String getGoodbyeAnswerIfExit(@NotNull final String question) {
    if (question.equalsIgnoreCase(localizationHelper.getMessage(QUESTION_COMMON_EXIT))) {
      return localizationHelper.getMessage(ANSWER_COMMON_BYE);
    }
    return null;
  }

  /**
   * Get an answer to the 'who is the department's head' question .
   * @param  departmentName {@link Department}'s name
   * @return message
   */
  @NotNull
  public String getDepartmentsHeadNameAnswer(@NotNull final String departmentName) {
    Department department = departmentService.getDepartmentByName(departmentName);

    return String.format(localizationHelper.getMessage(ANSWER_DEPARTMENT_HEAD_OF), department.getName(), department.getHead().getName());
  }

  /**
   * Get an answer to the 'department's statistic' question .
   * @param  departmentName {@link Department}'s name
   * @return message
   */
  @NotNull
  public String getDepartmentEmployeeStatisticsAnswer(@NotNull final String departmentName) {

    Department department = departmentService.getDepartmentByName(departmentName);

    int assistCount = department.getEmployees().stream()
        .filter(employee -> employee.getDegree().equals(Employee.Degree.ASSISTANT))
        .collect(Collectors.toList()).size();

    int assocProfCount = department.getEmployees().stream()
        .filter(employee -> employee.getDegree().equals(Employee.Degree.ASSOCIATE_PROFESSOR))
        .collect(Collectors.toList()).size();

    int profCount = department.getEmployees().stream()
        .filter(employee -> employee.getDegree().equals(Employee.Degree.PROFESSOR))
        .collect(Collectors.toList()).size();

    return String.format(localizationHelper.getMessage(ANSWER_DEPARTMENT_STATISTICS), assistCount, assocProfCount, profCount);
  }

  /**
   * Get an answer to the 'department's average salary' question .
   * @param  departmentName {@link Department}'s name
   * @return message
   */
  @NotNull
  public String getAvgSalaryStatisticsAnswer(@NotNull final String departmentName) {

    Department department = departmentService.getDepartmentByName(departmentName);

    DoubleSummaryStatistics statistics = department.getEmployees()
        .stream()
        .mapToDouble(Employee::getSalary)
        .summaryStatistics();

    Double avgSalary = statistics.getAverage();
    return String.format(localizationHelper.getMessage(ANSWER_DEPARTMENT_SALARY), department.getName(), avgSalary);
  }

  /**
   * Get an answer to the 'department's employee count' question .
   * @param  departmentName {@link Department}'s name
   * @return message
   */

  @NotNull
  public String getEmployeeCountAnswer(@NotNull final String departmentName) {

    Department department = departmentService.getDepartmentByName(departmentName);

    return department.getEmployees().size() + "";
  }

  /**
   * Get an answer to the 'global employee search' question .
   * @param  subName sub name to search
   * @return message
   */

  @NotNull
  public String getGlobalEmployeeSearchAnswer(@NotNull final String subName) {

    List<Employee> employees = employeeService.getEmployees(subName);

    if (employees.isEmpty()) {
      return localizationHelper.getMessage(ANSWER_EMPLOYEE_NOTHING_FOUND);
    }

    StringBuilder sb = new StringBuilder();
    for (Employee employee: employees) {
      sb.append(employee.getName()).append(", ");
    }

    return sb.toString().substring(0, sb.length() - 2); // remove last comma
  }

  /**
   * Get an invalid input message .
   * @return message
   */

  @NotNull
  public String getIncorrectInputAnswer() {
    return localizationHelper.getMessage(ANSWER_COMMON_INCORRECT_QUESTION);
  }
}
