package com.martynenko.anton.university;

import com.martynenko.anton.university.department.Department;
import com.martynenko.anton.university.department.DepartmentService;
import com.martynenko.anton.university.employee.Employee;
import com.martynenko.anton.university.employee.EmployeeService;
import com.martynenko.anton.university.i18n.LocalizationHelper;
import com.martynenko.anton.university.ui.AnswerService;
import com.martynenko.anton.university.ui.QuestionDistributor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static com.martynenko.anton.university.i18n.MessageCode.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UniversityApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
class QuestionDistributorTest {

  @InjectMocks
  private QuestionDistributor questionDistributor;

  @Mock
  private LocalizationHelper localizationHelper;

  @Mock
  private AnswerService answerService;

  private static final String question = "question";
  private static final String extraction = "extraction";
  private static final String answer = "answer";

  @Test
  void contextLoads(){
    assertThat(questionDistributor).isNotNull();
  }

  @BeforeEach
  void prepareMocks(){
    Mockito.clearInvocations(localizationHelper, answerService);
  }

  @Test
  void shouldReturnDepartmentsHeadNameAnswer() {

    when(localizationHelper.extract(question, QUESTION_DEPARTMENT_HEAD_OF)).thenReturn(extraction);
    when(answerService.getDepartmentsHeadNameAnswer(extraction)).thenReturn(answer);

    assertEquals(ReflectionTestUtils.invokeMethod(questionDistributor, "getAnswer", question), answer);
  }

  @Test
  void shouldReturnDepartmentEmployeeStatisticsAnswer(){
    when(localizationHelper.extract(question, QUESTION_DEPARTMENT_STATISTICS)).thenReturn(extraction);
    when(answerService.getDepartmentEmployeeStatisticsAnswer(extraction)).thenReturn(answer);
    assertEquals(ReflectionTestUtils.invokeMethod(questionDistributor, "getAnswer", question), answer);
  }

  @Test
  void shouldReturnAvgSalaryStatisticsAnswer(){

    when(localizationHelper.extract(question, QUESTION_DEPARTMENT_SALARY)).thenReturn(extraction);
    when(answerService.getAvgSalaryStatisticsAnswer(extraction)).thenReturn(answer);

    assertEquals(ReflectionTestUtils.invokeMethod(questionDistributor, "getAnswer", question), answer);
  }

  @Test
  void shouldReturnEmployeeCountAnswer(){

    when(localizationHelper.extract(question, QUESTION_DEPARTMENT_COUNT)).thenReturn(extraction);
    when(answerService.getEmployeeCountAnswer(extraction)).thenReturn(answer);

    assertEquals(ReflectionTestUtils.invokeMethod(questionDistributor, "getAnswer", question), answer);
  }

  @Test
  void shouldReturnGlobalEmployeeSearchAnswer(){
    when(localizationHelper.extract(question, QUESTION_EMPLOYEE_SEARCH)).thenReturn(extraction);

    when(answerService.getGlobalEmployeeSearchAnswer(extraction)).thenReturn(answer);

    assertEquals(ReflectionTestUtils.invokeMethod(questionDistributor, "getAnswer", question), answer);
  }

  @Test
  void shouldReturnGlobalIncorrectInputAnswer(){

    when(answerService.getIncorrectInputAnswer()).thenReturn(answer);

    assertEquals(ReflectionTestUtils.invokeMethod(questionDistributor, "getAnswer", question), answer);
  }
}