
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
