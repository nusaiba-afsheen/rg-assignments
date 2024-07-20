package com.example.employee;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String INSERT_EMPLOYEE = "INSERT INTO employees (id, name, department) VALUES (?, ?, ?)";
    private static final String UPDATE_EMPLOYEE = "UPDATE employees SET name = ?, department = ? WHERE id = ?";
    private static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE id = ?";
    private static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE id = ?";
    private static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employees";

    public void create(Employee employee) {
        jdbcTemplate.update(INSERT_EMPLOYEE, employee.getId(), employee.getName(), employee.getDepartment());
    }

    public void update(Employee employee) {
        jdbcTemplate.update(UPDATE_EMPLOYEE, employee.getName(), employee.getDepartment(), employee.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE_EMPLOYEE, id);
    }

    public Optional<Employee> findById(int id) {
        return jdbcTemplate.queryForObject(SELECT_EMPLOYEE_BY_ID, new Object[]{id}, (rs, rowNum) -> Optional.of(mapRowToEmployee(rs)));
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query(SELECT_ALL_EMPLOYEES, (rs, rowNum) -> mapRowToEmployee(rs));
    }

    private Employee mapRowToEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setDepartment(rs.getString("department"));
        return employee;
    }
}
