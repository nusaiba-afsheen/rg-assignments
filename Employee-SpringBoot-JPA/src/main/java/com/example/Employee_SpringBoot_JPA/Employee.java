package com.example.Employee_SpringBoot_JPA;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "EmployeeTable")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String department;
}

