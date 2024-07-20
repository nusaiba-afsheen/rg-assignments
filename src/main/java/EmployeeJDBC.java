import org.example.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeJDBC {
    private static final String URL = "jdbc:mysql://localhost:3306/EmployeeDB";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Create
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (id, name, department) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getDepartment());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new employee was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read all employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");

                Employee employee = new Employee(id, name, department);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // Read employee by ID
    public Employee getEmployeeById(int id) {
        Employee employee = null;
        String sql = "SELECT * FROM employees WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                employee = new Employee(id, name, department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    // Update
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET name = ?, department = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getDepartment());
            statement.setInt(3, employee.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing employee was updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An employee was deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        EmployeeJDBC employeeJDBC = new EmployeeJDBC();

        // Create
        Employee emp1 = new Employee(1, "Alice", "HR");
        Employee emp2 = new Employee(2, "Bob", "IT");
        Employee emp3 = new Employee(3, "Charlie", "Finance");

        employeeJDBC.addEmployee(emp1);
        employeeJDBC.addEmployee(emp2);
        employeeJDBC.addEmployee(emp3);

        // Read all
        List<Employee> employees = employeeJDBC.getAllEmployees();
        System.out.println("All Employees:");
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        // Read by ID
        System.out.println("Get Employee with ID 2:");
        Employee emp = employeeJDBC.getEmployeeById(2);
        System.out.println(emp);

        // Update
        Employee updatedEmp = new Employee(2, "Bob Updated", "IT Updated");
        employeeJDBC.updateEmployee(updatedEmp);

        // Delete
        employeeJDBC.deleteEmployee(1);

        // Final list of employees
        System.out.println("All Employees after operations:");
        employees = employeeJDBC.getAllEmployees();
        for (Employee e : employees) {
            System.out.println(e);
        }
    }
}

