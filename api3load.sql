CREATE TABLE public.reimbs_user (
	id serial4 NOT NULL,
	first_name varchar(20) NOT NULL,
	last_name varchar(40) NOT NULL,
	email varchar(40) NOT NULL,
	"password" varchar(100) NOT NULL,
	"role" varchar(8) NOT NULL,
	manager_id int8 NULL,
	CONSTRAINT reimbs_user_email_key UNIQUE (email),
	CONSTRAINT reimbs_user_pkey PRIMARY KEY (id),
	CONSTRAINT reimbs_user_role_check CHECK (((role)::text = ANY ('{ROLE_EMP,ROLE_MAN}'::text[]))),
	CONSTRAINT reimbs_user_manager_id_fkey FOREIGN KEY (manager_id) REFERENCES public.reimbs_user(id)
);

CREATE TABLE public.reimb (
	id serial4 NOT NULL,
	emp_user_id int8 NOT NULL,
	man_user_id int8 NOT NULL,
	"type" varchar(15) NOT NULL,
	amount float8 NOT NULL,
	status varchar(10) NOT NULL,
	CONSTRAINT reimb_pkey PRIMARY KEY (id),
	CONSTRAINT reimb_status_check CHECK (((status)::text = ANY ('{APPROVED,DENIED,PENDING}'::text[]))),
	CONSTRAINT reimb_type_check CHECK (((type)::text = ANY ('{TRAVEL,FOOD,REQUISITION,OTHER}'::text[])))
);


-- public.reimb foreign keys

ALTER TABLE public.reimb ADD CONSTRAINT reimb_emp_user_id_fkey FOREIGN KEY (emp_user_id) REFERENCES public.reimbs_user(id);
ALTER TABLE public.reimb ADD CONSTRAINT reimb_man_user_id_fkey FOREIGN KEY (man_user_id) REFERENCES public.reimbs_user(id);






INSERT INTO public.reimbs_user
(id, first_name, last_name, email, "password", "role", manager_id)
VALUES(1, 'August', 'Duet', 'august.duet@revature.com', 'password123', 'ROLE_MAN', 1);
INSERT INTO public.reimbs_user
(id, first_name, last_name, email, "password", "role", manager_id)
VALUES(6, 'jack', 'Schuwan', 'jack.schuwan@revature.com', 'password123', 'ROLE_MAN', 6);
INSERT INTO public.reimbs_user
(id, first_name, last_name, email, "password", "role", manager_id)
VALUES(4, 'Jane', 'Smith', 'janeSmith@revature.com', 'password123', 'ROLE_EMP', 1);
INSERT INTO public.reimbs_user
(id, first_name, last_name, email, "password", "role", manager_id)
VALUES(5, 'Amy', 'Oliver', 'AmyOliver@revature.com', 'password123', 'ROLE_EMP', 1);
INSERT INTO public.reimbs_user
(id, first_name, last_name, email, "password", "role", manager_id)
VALUES(2, 'Steven', 'Kelsey', 'steven.kelsey@revature.com', 'password123', 'ROLE_EMP', 1);
INSERT INTO public.reimbs_user
(id, first_name, last_name, email, "password", "role", manager_id)
VALUES(3, 'John', 'Doe', 'john.doe@revature.com', 'password123', 'ROLE_EMP', 1);




INSERT INTO public.reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(1, 2, 1, 'FOOD', 20.0, 'PENDING');
INSERT INTO public.reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(2, 2, 1, 'TRAVEL', 20.0, 'PENDING');
INSERT INTO public.reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(3, 3, 1, 'TRAVEL', 20.0, 'PENDING');
INSERT INTO public.reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(4, 3, 1, 'FOOD', 20.0, 'PENDING');
INSERT INTO public.reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(5, 3, 1, 'FOOD', 20.0, 'PENDING');
INSERT INTO public.reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(8, 4, 1, 'FOOD', 20.0, 'PENDING');
INSERT INTO public.reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(9, 5, 1, 'TRAVEL', 20.0, 'PENDING');
INSERT INTO public.reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(10, 5, 1, 'FOOD', 20.0, 'PENDING');
INSERT INTO public.reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(6, 3, 1, 'FOOD', 20.0, 'APPROVED');
INSERT INTO public.reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(7, 3, 1, 'FOOD', 20.0, 'APPROVED');
INSERT INTO public.reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(12, 3, 1, 'TRAVEL', 20.0, 'APPROVED');
INSERT INTO public.reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(11, 5, 6, 'REQUISITION', 20.0, 'APPROVED');
INSERT INTO public.reimb
(id, emp_user_id, man_user_id, "type", amount, status)
VALUES(13, 3, 6, 'FOOD', 30.0, 'APPROVED');

