package com.example.Employee_SpringBoot_JPA.controller;

import com.example.Employee_SpringBoot_JPA.Employee;
import com.example.Employee_SpringBoot_JPA.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepo;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(@PathVariable Integer id){
        try{
            List<Employee> employeeList = new ArrayList<>(employeeRepo.findAll());

            if(employeeList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employeeList,HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id){

        Optional<Employee> employeeData = employeeRepo.findById(id);

        if (employeeData.isPresent()){
            return new ResponseEntity<>(employeeData.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp){
        Employee employeeObj = employeeRepo.save(emp);

        return new ResponseEntity<>(employeeObj, HttpStatus.OK);
    }

    @PostMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable Integer id,@RequestBody Employee newEmp){
            Optional<Employee> oldEmpData = employeeRepo.findById(id);

            try{
                if (oldEmpData.isPresent()){
                    Employee updatedEmpData = oldEmpData.get();
                    updatedEmpData.setName(newEmp.getName());
                    updatedEmpData.setDepartment(newEmp.getDepartment());

                    Employee empObj = employeeRepo.save(updatedEmpData);
                    return new ResponseEntity<>(empObj, HttpStatus.CREATED);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable Integer id){
        try{
            employeeRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
