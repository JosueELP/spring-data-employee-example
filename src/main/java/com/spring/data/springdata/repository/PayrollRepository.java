package com.spring.data.springdata.repository;

import com.spring.data.springdata.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Integer> {
}
