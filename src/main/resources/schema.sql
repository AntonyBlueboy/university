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