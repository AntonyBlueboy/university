package com.martynenko.anton.university.i18n;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Enumeration collecting codes of String resources in message.properties file .
 *
 * @author Martynenko Anton
 * @since 1.1
 */
@AllArgsConstructor
@Getter
@ToString
public enum MessageCode {

  // some of provided codes related to extraction patterns, some to simple String sentence
  // all of them could be internationalized in message.properties files

  QUESTION_DEPARTMENT_HEAD_OF("question.department.headOf"),
  QUESTION_DEPARTMENT_STATISTICS("question.department.statistics"),
  QUESTION_DEPARTMENT_SALARY("question.department.salary"),
  QUESTION_DEPARTMENT_COUNT("question.department.count"),
  QUESTION_EMPLOYEE_SEARCH("question.employee.search"),
  QUESTION_COMMON_EXIT("question.common.exit"),

  ANSWER_DEPARTMENT_NO_SUCH("answer.department.nosuch"),
  ANSWER_DEPARTMENT_HEAD_OF("answer.department.headOf"),
  ANSWER_DEPARTMENT_STATISTICS("answer.department.statistics"),
  ANSWER_DEPARTMENT_SALARY("answer.department.salary"),
  ANSWER_EMPLOYEE_NOTHING_FOUND("answer.employee.nothingfound"),
  ANSWER_COMMON_INCORRECT_QUESTION("answer.common.incorrectquestion"),
  ANSWER_COMMON_HI("answer.common.hi"),
  ANSWER_COMMON_BYE("answer.common.bye"),
  ANSWER_COMMON_ELSE("answer.common.else"),
  ANSWER_COMMON_EXCEPTION("answer.common.exception");

  /**
   * Code for string resource access .
   */
  private final String code;
}
