CREATE TABLE IF NOT EXISTS `employee` (
      `id`    INTEGER  PRIMARY KEY AUTO_INCREMENT,
      `name`  VARCHAR(50) NOT NULL,
      `salary` FLOAT NOT NULL,
      `degree` ENUM ('assistant', 'associate professor', 'professor')
);

CREATE TABLE IF NOT EXISTS `department` (
    `id`    INTEGER  PRIMARY KEY AUTO_INCREMENT,
    `name`  VARCHAR(50) NOT NULL,
    `head_id` INTEGER NOT NULL,
    FOREIGN KEY (`head_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `department_employee` (
     `department_id` INTEGER ,
     `employee_id` INTEGER ,
     PRIMARY KEY (`department_id`, `employee_id`),
     FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
     FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

insert into employee values (null, 'Alexander von Humboldt', 1700, 'professor'),
                            (null, 'Mykhailo Hrushevsky', 1300, 'associate professor'),
                            (null, 'Diogenes the Cynic', 700, 'assistant'),
                            (null, 'Leonardo da Vinci', 300, 'professor'),
                            (null, 'Albert Einstein', 3300, 'associate professor'),
                            (null, 'Galileo Galilei', 2550, 'professor'),
                            (null, 'Herodotus ', 2000, 'assistant');

insert into department values (null, 'Geography', 1),
                                (null, 'History', 2),
                                (null, 'Philosophy', 3),
                                (null, 'Physics', 4);

insert into department_employee values (1, 1),
                                        (2, 2),
                                        (3, 3),
                                        (4, 4),
                                        (3, 4),
                                        (4, 5),
                                        (1, 6),
                                        (3, 6),
                                        (1, 7),
                                        (2, 7),
                                        (3, 7);
