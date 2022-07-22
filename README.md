# University

University is demo project. It uses java 11 and spring boot 2 and H2 embedded relative database. Tests are Junit 5 based.

## The specification

Create a simple Spring Boot java project with the console interface for university, which consists of departments and lectors. The lectors could work in more than one department. A lector could have one degree (assistant, associate professor, professor).

All data is stored in the relational database.  

The app should implement such commands:

1.User Input: 
```bash
Who is head of department {department_name}
```
Answer: 
```bash
Head of {department_name} department is {head_of_department_name}
```

2.User Input: 
```bash
Show {department_name} statistics.
```
Answer: 
```bash
assistans - {assistams_count}. 
associate professors - {associate_professors_count}
professors -{professors_count}
```

3.User Input: 
```bash
Show the average salary for the department {department_name}.
```
Answer: 
```bash
The average salary of {department_name} is {average_salary}
```

4.User Input: 
```bash
Show count of employee for {department_name}.
```
Answer: 
```bash
{employee_count}
```

5.User Input: 
```bash
Global search by {template}.
```
Answer: 
```bash
Ivan Petrenko, Petro Ivanov
```

## Requirements
To build and run application you need Java version not older then 11 and Apache Maven 3.


## Building
Run next script in root directory
```bash
mvn clean package spring-boot:repackage
```
## Run
Builded .jar file could be run using
```bash
java -jar target/university-1.1.0.jar
```


You can also see [javadoc](https://antonmartynenko13.github.io/university/apidocs)

And other project [details](https://antonmartynenko13.github.io/university/)

## License
[MIT](https://github.com/antonmartynenko13/university/blob/main/LICENSE)