CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE employee
(
    employee_id INTEGER       NOT NULL,
    first_name  VARCHAR(255)  NOT NULL,
    last_name   VARCHAR(255)  NOT NULL,
    salary      NUMERIC(6, 2) NOT NULL,
    PRIMARY KEY (employee_id)
);

CREATE TABLE payroll
(
    employee_id  INTEGER       NOT NULL,
    total_salary NUMERIC(6, 2) NOT NULL,
    days_worked  INTEGER       NOT NULL,
    PRIMARY KEY (employee_id),
    CONSTRAINT fk_employee_id FOREIGN KEY (employee_id) REFERENCES employee ON DELETE CASCADE
);