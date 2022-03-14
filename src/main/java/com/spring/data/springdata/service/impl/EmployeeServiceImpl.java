package com.spring.data.springdata.service.impl;


import com.spring.data.springdata.api.request.NewEmployeeRequest;
import com.spring.data.springdata.dto.EmployeeDTO;
import com.spring.data.springdata.entity.Employee;
import com.spring.data.springdata.repository.EmployeeRepository;
import com.spring.data.springdata.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

//import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final ModelMapper mapper;

    public EmployeeServiceImpl(EmployeeRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        log.debug("Getting all employees");

        List<Employee> employees = repository.findAll();

        return employees.stream()
                .map(employee -> mapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO createEmployee(NewEmployeeRequest newEmployee) {
        log.debug("Saving new employee: {}", newEmployee);

        Employee newEntity = mapper.map(newEmployee, Employee.class);
        Employee savedEmployee = repository.save(newEntity);

        return mapper.map(savedEmployee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getEmployee(Integer id) {
        log.debug("Getting employee with id: {}", id);

        Optional<Employee> savedEmployee = repository.findById(id);

        if (savedEmployee.isPresent()) {
            return mapper.map(savedEmployee.get(), EmployeeDTO.class);
        }

        //throw new NotFoundException("Employee not found");
        throw new IllegalStateException("Employee not found");
    }

    @Override
    public List<EmployeeDTO> getEmployeesBySalary(Double salary) {
        log.debug("Getting employees with salary >= : {}", salary);

        List<Employee> employees = repository.findAllBySalaryGreaterThanEqual(salary);

        return employees.stream()
                .map(employee -> mapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public String deleteEmployee(Integer id) {
        log.debug("Deleting employee with ID: {}", id);
        Optional<Employee> savedEmployee = repository.findById(id);

        if(savedEmployee.isEmpty()){
            throw new IllegalStateException("Employee not found");
        }

        repository.deleteById(id);
        return "Employee {" + id + "} deleted";
    }
}
