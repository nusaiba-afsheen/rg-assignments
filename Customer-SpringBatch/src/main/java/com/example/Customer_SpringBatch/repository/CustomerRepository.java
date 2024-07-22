package com.example.Customer_SpringBatch.repository;

import com.example.Customer_SpringBatch.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer,Integer> {
}
