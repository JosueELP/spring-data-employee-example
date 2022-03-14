package com.spring.data.springdata.api;

import com.spring.data.springdata.api.request.NewEmployeeRequest;
import com.spring.data.springdata.dto.EmployeeAndPayrollDTO;
import com.spring.data.springdata.dto.EmployeeDTO;
import com.spring.data.springdata.service.EmployeeService;
import com.spring.data.springdata.service.PayrollService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/employees")
public class EmployeeAPI {

    private final EmployeeService employeeService;
    private final PayrollService payrollService;

    public EmployeeAPI(EmployeeService employeeService, PayrollService payrollService) {
        this.employeeService = employeeService;
        this.payrollService = payrollService;
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Integer employeeId) {
        EmployeeDTO foundEmployee = employeeService.getEmployee(employeeId);
        log.info("Employee found: {}", foundEmployee);

        return foundEmployee;
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> foundEmployees = employeeService.getEmployees();
        log.info("Employees found: {}", foundEmployees);

        return foundEmployees;
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody NewEmployeeRequest newEmployee) {
        EmployeeDTO employee = employeeService.createEmployee(newEmployee);
        log.info("New employee: {}", employee);

        return employee;
    }

    @GetMapping("/bySalary")
    public List<EmployeeDTO> getEmployeeBySalary(@RequestParam Double salary) {
        List<EmployeeDTO> foundEmployees = employeeService.getEmployeesBySalary(salary);
        log.info("Employees found: {}", foundEmployees);

        return foundEmployees;
    }

    // POST v1/employee/{employeeId}?workedDays=6
    // 1. Agregar un registro nuevo a la tabla payroll:
    // Se debe actualizar el salario total de un empleado: total_salary = salary * workedDays
    // El endpoint debe regresar un objecto nuevo:
    // { "fullname":"nombre completo", "salary": 100.00, "daysWorked": 6, "totalSalary": "$600.00" (<= atencion al formato)}
    @PostMapping("/{employeeId}")
    public EmployeeAndPayrollDTO createPayroll(@PathVariable Integer employeeId, @RequestParam Integer workedDays){
        EmployeeDTO employee = employeeService.getEmployee(employeeId);
        EmployeeAndPayrollDTO payroll = payrollService.createPayroll(employeeId, employee, workedDays);
        log.info("New payroll: {}", payroll);

        return payroll;
    }

    // GET v1/employee/{employeeId}/totalSalary
    // 2. Debe regresar el salario total del emepleado
    // El endpoint debe regresar un objecto como el enpoint anterior:
    // { "fullname":"nombre completo", "salary": 100.00, "daysWorked": 6, "totalSalary": "$600.00" (<= atencion al formato)}
    @GetMapping("/{employeeId}/totalSalary")
    public EmployeeAndPayrollDTO getPayroll(@PathVariable Integer employeeId){
        EmployeeDTO employee = employeeService.getEmployee(employeeId);
        EmployeeAndPayrollDTO payroll = payrollService.getPayroll(employeeId, employee);
        log.info("Payroll found: {}", payroll);

        return payroll;
    }

    /*** BONUS ***/
    // DELETE v1/employee/{employeeId}
    // 3. Elimina un empleado por completo
    // El endpoint regresa un String: "Empleado {employeeId} eliminado"
    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable Integer employeeId){
        if(payrollService.deletePayroll(employeeId)){
            return employeeService.deleteEmployee(employeeId);
        }
        
        return "Error while deleting employee, please check log results for more info";
    }

    // PUT v1/employee/{employeeId}?workedDays=7
    // 4. Actualiza el registro de un empleado la tabla payroll:
    // Se debe actualizar el salario total de un empleado: total_salary = salary * workedDays
    // SOLO SI EL NUEVO VALOR DE DIAS TOTALES EN MAYOR QUE EL ANTERIOR
    // El endpoint debe regresar un objecto:
    // { "fullname":"nombre completo", "salary": 100.00, "daysWorked": 7, "totalSalary": "$700.00" (<= atencion al formato)}
    @PutMapping("/{employeeId}")
    public EmployeeAndPayrollDTO updatePayroll(@PathVariable Integer employeeId, @RequestParam Integer workedDays){
        EmployeeDTO employee = employeeService.getEmployee(employeeId);

        return payrollService.updatePayroll(employeeId, employee, workedDays);
    }

}