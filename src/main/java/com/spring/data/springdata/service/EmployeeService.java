package com.spring.data.springdata.service;

import com.spring.data.springdata.api.request.NewEmployeeRequest;
import com.spring.data.springdata.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getEmployees();

    EmployeeDTO createEmployee(NewEmployeeRequest newEmployee);

    EmployeeDTO getEmployee(Integer id);

    List<EmployeeDTO> getEmployeesBySalary(Double salary);

    String deleteEmployee(Integer id);
}
