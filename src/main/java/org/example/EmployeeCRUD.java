package org.example;


import java.util.ArrayList;
import java.util.List;

public class EmployeeCRUD {
    private List<Employee> employees = new ArrayList<>();

    // Create
    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added: " + employee);
    }

    // Read
    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee getEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null; // or throw an exception
    }

    // Update
    public void updateEmployee(int id, String name, String department) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setName(name);
            employee.setDepartment(department);
            System.out.println("Employee updated: " + employee);
        } else {
            System.out.println("Employee not found with id: " + id);
        }
    }

    // Delete
    public void deleteEmployee(int id) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employees.remove(employee);
            System.out.println("Employee removed: " + employee);
        } else {
            System.out.println("Employee not found with id: " + id);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        EmployeeCRUD employeeCRUD = new EmployeeCRUD();

        Employee emp1 = new Employee(1, "Alice", "HR");
        Employee emp2 = new Employee(2, "Bob", "IT");
        Employee emp3 = new Employee(3, "Charlie", "Finance");

        // Create
        employeeCRUD.addEmployee(emp1);
        employeeCRUD.addEmployee(emp2);
        employeeCRUD.addEmployee(emp3);

        // Read
        System.out.println("All Employees: " + employeeCRUD.getAllEmployees());
        System.out.println("Get Employee with ID 2: " + employeeCRUD.getEmployeeById(2));

        // Update
        employeeCRUD.updateEmployee(2, "Bob Updated", "IT Updated");

        // Delete
        employeeCRUD.deleteEmployee(1);

        // Final list of employees
        System.out.println("All Employees after operations: " + employeeCRUD.getAllEmployees());
    }
}

