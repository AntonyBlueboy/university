insert into employee (`name`, `salary`, `degree`) values ('Alexander von Humboldt', 1700, 'PROFESSOR'),
                            ('Mykhailo Hrushevsky', 1300, 'ASSOCIATE_PROFESSOR'),
                            ('Diogenes the Cynic', 700, 'ASSISTANT'),
                            ('Leonardo da Vinci', 300, 'PROFESSOR'),
                            ('Albert Einstein', 3300, 'ASSOCIATE_PROFESSOR'),
                            ('Galileo Galilei', 2550, 'PROFESSOR'),
                            ('Herodotus', 2000, 'ASSISTANT');

insert into department (`name`, `head_id`) values ('Geography', 1),
                                ('History', 2),
                                ('Philosophy', 3),
                                ('Physics', 4);

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