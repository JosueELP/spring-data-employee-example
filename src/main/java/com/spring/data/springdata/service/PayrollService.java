package com.spring.data.springdata.service;

import com.spring.data.springdata.dto.EmployeeAndPayrollDTO;
import com.spring.data.springdata.dto.EmployeeDTO;

public interface PayrollService {

    EmployeeAndPayrollDTO createPayroll(Integer Id, EmployeeDTO employee, Integer daysWorked);

    EmployeeAndPayrollDTO getPayroll(Integer id, EmployeeDTO employee);

    EmployeeAndPayrollDTO updatePayroll(Integer Id, EmployeeDTO employee, Integer daysWorked);

    Boolean deletePayroll(Integer id);
}
