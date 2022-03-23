package com.university.university;

import com.sun.istack.NotNull;
import com.university.university.model.Department;
import com.university.university.model.Employee;
import com.university.university.service.DepartmentService;
import com.university.university.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class UniversityApplication implements CommandLineRunner {

	//questions patterns strings
	public static final String HEAD_DEPARTMENT_STARTS_WITH = "Who is head of department ";
	public static final String STATISTIC_STARTS_WITH = "Show ";
	public static final String STATISTIC_ENDS_WITH = " statistics.";
	public static final String SALARY_STARTS_WITH = "Show the average salary for the department ";
	public static final String EMPLOYEES_COUNT_STARTS_WITH = "Show count of employee for ";
	public static final String GLOBAL_SEARCH_STARTS_WITH = "Global search by ";
	public static final String EXIT = "exit";

	//answers patterns strings
	public static final String WHAT_DO_YOU_WANT = "What do you want to know?";
	public static final String ANYTHING_ELSE = "Anything else?";
	public static final String NO_SUCH_DEPARTMENT = "No such department";
	public static final String HEAD_DEPARTMENT_ANSWER_PATTERN = "Head of %1$s department is %2$s";
	public static final String STATISTIC_ANSWER_PATTERN = "Answer: assistans - %1$d. \n" +
			"        associate professors - %2$d\n" +
			"        professors - %3$d";
	public static final String SALARY_ANSWER_PATTERN = "Answer: The average salary of %1$s is %2$.2f";
	public static final String ANSWER = "Answer: ";
	public static final String NOTHING_FOUND = "nothing found";
	public static final String INCORRECT_QUESTION = "The question is incorrect";

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		System.out.println(WHAT_DO_YOU_WANT);

		while  (scanner.hasNext()) {
			String nextline = scanner.nextLine();
			if (nextline.equalsIgnoreCase(EXIT)) break;
			System.out.println(answerTheQuestion(nextline));
			System.out.println(ANYTHING_ELSE);
		}
		scanner.close();
	}

	/**
	 * Returns an complete answer or a warning.
	 *
	 * @param  question  users input
	 * @return      answer or a warning String
	 */

	public @NotNull String answerTheQuestion(@NotNull String question){

		//question number 1
		if (question.startsWith(HEAD_DEPARTMENT_STARTS_WITH)){
			String departmentName = question.replace(HEAD_DEPARTMENT_STARTS_WITH, "").trim();
			Department department = departmentService.getDepartmentByName(departmentName);
			if (department == null) return NO_SUCH_DEPARTMENT;

			return String.format(HEAD_DEPARTMENT_ANSWER_PATTERN, departmentName, department.getHead().getName());

		//question number 2
		} else if (question.matches(String.format("^%1$s.*%2$s$", STATISTIC_STARTS_WITH, STATISTIC_ENDS_WITH))){ //check question by start and end
			String departmentName = question.replace(STATISTIC_STARTS_WITH, "").replace(STATISTIC_ENDS_WITH, "").trim();
			Department department = departmentService.getDepartmentByName(departmentName);
			if (department == null) return NO_SUCH_DEPARTMENT;

			int assistCount = 0;
			int assocProfCount = 0;
			int profCount = 0;

			for(Employee employee: department.getEmployees()){
				switch (employee.getDegree()){
					case Employee.ASSISTANT: 			assistCount++;
														break;
					case Employee.ASSOCIATE_PROFESSOR: 	assocProfCount++;
														break;
					case Employee.PROFESSOR:			profCount++;
														break;
				}
			}

			return String.format(STATISTIC_ANSWER_PATTERN, assistCount, assocProfCount, profCount);

		//question number 3
		} else if(question.startsWith(SALARY_STARTS_WITH)){
			String departmentName = question.replace(SALARY_STARTS_WITH, "").trim();
			Department department = departmentService.getDepartmentByName(departmentName);
			if (department == null) return NO_SUCH_DEPARTMENT;

			Double avgSalary = employeeService.countAvgSalary(department);
			return String.format(SALARY_ANSWER_PATTERN, departmentName, avgSalary);

		//question number 4
		} else if(question.startsWith(EMPLOYEES_COUNT_STARTS_WITH)){
			String departmentName = question.replace(EMPLOYEES_COUNT_STARTS_WITH, "").trim();
			Department department = departmentService.getDepartmentByName(departmentName);
			if (department == null) return NO_SUCH_DEPARTMENT;

			return String.format("%1$s %2$d",ANSWER, department.getEmployees().size());

		//question number 5
		} else if (question.startsWith(GLOBAL_SEARCH_STARTS_WITH)){
			String subName = question.replace(GLOBAL_SEARCH_STARTS_WITH, "").trim();
			List<Employee> employees = employeeService.getEmployees(subName);
			StringBuilder sb = new StringBuilder(ANSWER);
			for (Employee employee: employees){
				sb.append(employee.getName()).append(",");
			}

			if (sb.toString().replace(ANSWER, "").trim().isEmpty()){
				return sb.append(NOTHING_FOUND).toString();
			} else {
				return sb.toString().substring(0, sb.length() - 1); // remove last comma
			}

		//incorrect input
		} else {
			return INCORRECT_QUESTION;
		}
	}
}
