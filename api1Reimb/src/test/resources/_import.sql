INSERT INTO reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(1, 2, 1, 'FOOD', 20.0, 'PENDING');
INSERT INTO reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(2, 2, 1, 'TRAVEL', 20.0, 'PENDING');
INSERT INTO reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(3, 3, 1, 'TRAVEL', 20.0, 'PENDING');
INSERT INTO reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(4, 3, 1, 'FOOD', 20.0, 'PENDING');
INSERT INTO reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(5, 3, 1, 'FOOD', 20.0, 'PENDING');
INSERT INTO reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(8, 4, 1, 'FOOD', 20.0, 'PENDING');
INSERT INTO reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(9, 5, 1, 'TRAVEL', 20.0, 'PENDING');
INSERT INTO reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(10, 5, 1, 'FOOD', 20.0, 'PENDING');
INSERT INTO reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(11, 5, 1, 'REQUISITION', 20.0, 'PENDING');
INSERT INTO reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(6, 3, 1, 'FOOD', 20.0, 'APPROVED');
INSERT INTO reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(7, 3, 1, 'FOOD', 20.0, 'APPROVED');
INSERT INTO reimbs_user
(id, first_name, last_name, email, "password", "role", manager_id)
VALUES(3, 'John', 'Doe', 'john.doe@revature.com', 'password123', 'ROLE_EMP', 1);
INSERT INTO reimbs_user
(id, first_name, last_name, email, "password", "role", manager_id)
VALUES(1, 'August', 'Duet', 'august.duet@revature.com', 'password123', 'ROLE_MAN', 1);
INSERT INTO reimbs_user
(id, first_name, last_name, email, "password", "role", manager_id)
VALUES(4, 'Jane', 'Smith', 'janeSmith@revature.com', 'password123', 'ROLE_EMP', 1);
INSERT INTO reimbs_user
(id, first_name, last_name, email, "password", "role", manager_id)
VALUES(5, 'Amy', 'Oliver', 'AmyOliver@revature.com', 'password123', 'ROLE_EMP', 1);
INSERT INTO reimbs_user
(id, first_name, last_name, email, "password", "role", manager_id)
VALUES(2, 'Steven', 'Kelsey', 'steven.kelsey@revature.com', 'password123', 'ROLE_EMP', 1);
