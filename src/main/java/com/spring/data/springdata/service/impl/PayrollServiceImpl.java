package com.spring.data.springdata.service.impl;

import com.spring.data.springdata.dto.EmployeeAndPayrollDTO;
import com.spring.data.springdata.dto.EmployeeDTO;
import com.spring.data.springdata.entity.Payroll;
import com.spring.data.springdata.repository.PayrollRepository;
import com.spring.data.springdata.service.PayrollService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PayrollServiceImpl implements PayrollService {

    private final PayrollRepository payrollRepository;

    public PayrollServiceImpl(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    @Override
    public EmployeeAndPayrollDTO createPayroll(Integer id, EmployeeDTO employee, Integer daysWorked) {
        log.debug("Creating new payroll with ID {}", id);
        Optional<Payroll> payroll = payrollRepository.findById(id);
        if(payroll.isPresent()){
            throw new IllegalStateException("There is already a payroll with that Employee ID, try with PUT HTTP method instead");
        }

        double totalSalary = daysWorked * employee.getSalary();
        Payroll newPayroll = new Payroll(id, totalSalary, daysWorked);

        payrollRepository.save(newPayroll);
        return createDTO(newPayroll, employee);
    }

    @Override
    public EmployeeAndPayrollDTO getPayroll(Integer id, EmployeeDTO employee) {
        log.debug("Getting payroll with ID {}", id);
        Optional<Payroll> payroll = payrollRepository.findById(id);
        if(payroll.isEmpty()){
            throw new IllegalStateException("This Employee has not payrolls yet");
        }

        return createDTO(payroll.get(), employee);
    }

    @Override
    public Boolean deletePayroll(Integer id) {
        log.debug("Deleting payroll with ID {}", id);
        Optional<Payroll> payroll = payrollRepository.findById(id);
        if(payroll.isEmpty()){
            throw new IllegalStateException("Employee ID not found or this Employee has not payrolls yet");
        }

        payrollRepository.deleteById(id);
        return true;
    }

    @Override
    public EmployeeAndPayrollDTO updatePayroll(Integer id, EmployeeDTO employee, Integer newDaysWorked) {
        log.debug("Updating payroll with ID {}", id);
        Optional<Payroll> payroll = payrollRepository.findById(id);
        if(payroll.isEmpty()){
            throw new IllegalStateException("This Employee has not payrolls yet");
        }

        Payroll actualPayroll = payroll.get();
        if(newDaysWorked > actualPayroll.getDaysWorked()){
            actualPayroll.setDaysWorked(newDaysWorked);

            double newTotalSalary = newDaysWorked * employee.getSalary();
            actualPayroll.setTotalSalary(newTotalSalary);
        }else{
            throw new IllegalStateException("The new workdays has the same value (or less) as the current one in employee with ID {" + id + "}");
        }

        payrollRepository.save(actualPayroll);
        return createDTO(actualPayroll, employee);
    }

    public EmployeeAndPayrollDTO createDTO(Payroll payroll, EmployeeDTO employee){
        String totalSalaryFormat = "$"+payroll.getTotalSalary();
        return new EmployeeAndPayrollDTO(employee.getFullName(), employee.getSalary(), payroll.getDaysWorked(), totalSalaryFormat);
    }
}
