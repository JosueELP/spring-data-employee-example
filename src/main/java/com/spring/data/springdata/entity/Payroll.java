package com.spring.data.springdata.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "payroll")
public class Payroll {

    //El employee id es una llave foranea y esa ya se genero en la tabla employee,
    // y no deberia ser auto generada para la tabla payroll ;)
    @Id
    @Column(name = "employee_id", columnDefinition = "serial")
    Integer employeeId;

    @Column(name = "total_salary")
    Double totalSalary;

    @Column(name = "days_worked")
    Integer daysWorked;

    public Payroll(Integer employeeId, Double totalSalary, Integer daysWorked) {
        this.employeeId = employeeId;
        this.totalSalary = totalSalary;
        this.daysWorked = daysWorked;
    }
}

