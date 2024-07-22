package com.example.Employee_SpringBoot_JPA.repo;


import com.example.Employee_SpringBoot_JPA.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
