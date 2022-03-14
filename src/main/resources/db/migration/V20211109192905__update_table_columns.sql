-- Employee table update
ALTER TABLE employee
    DROP COLUMN employee_id CASCADE;
ALTER TABLE employee
    ADD COLUMN employee_id SERIAL PRIMARY KEY;
ALTER TABLE employee
    ALTER COLUMN salary TYPE NUMERIC(8, 2);

-- Payroll table update
ALTER TABLE payroll
    ADD CONSTRAINT fk_employee_id FOREIGN KEY (employee_id) REFERENCES employee (employee_id) ON DELETE CASCADE;
ALTER TABLE payroll
    ALTER COLUMN total_salary TYPE NUMERIC(8, 2);