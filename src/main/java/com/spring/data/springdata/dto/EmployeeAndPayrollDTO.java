package com.spring.data.springdata.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmployeeAndPayrollDTO {
    String fullName;
    Double salary;
    Integer daysWorked;
    String totalSalary;

    public EmployeeAndPayrollDTO(String fullName, Double salary, Integer daysWorked, String totalSalary) {
        this.fullName = fullName;
        this.salary = salary;
        this.daysWorked = daysWorked;
        this.totalSalary = totalSalary;
    }

}
