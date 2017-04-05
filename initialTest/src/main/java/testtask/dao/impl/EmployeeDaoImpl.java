package testtask.dao.impl;

import testtask.util.db.DatabaseConnection;
import testtask.dao.EmployeeDao;
import testtask.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    private Employee fillEmployeeWithFields(Employee employee, PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setBirthDate(rs.getDate("birth_date"));
                employee.setDepartmentId(rs.getInt("department_id"));
            }
        }
        return employee;
    }

    @Override
    public Employee findById(Integer id) throws SQLException {

        Employee employee = new Employee();
        String sql = "SELECT * FROM employees WHERE id=?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            employee = fillEmployeeWithFields(employee,preparedStatement);
        }
        return employee;
    }

    @Override
    public Employee findByEmail(String employeeEmail) throws SQLException {
        Employee employee = new Employee();
        String sql = "SELECT * FROM employees WHERE email=?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employeeEmail);
            employee = fillEmployeeWithFields(employee,preparedStatement);
            }
        return employee;
    }

    @Override
    public List<Employee> findAll( Integer departmentId) throws SQLException {

        List<Employee> employees = new ArrayList<Employee>();
        String sql ="SELECT id,name,email,salary,birth_date,department_id FROM employees WHERE department_id="+departmentId;

        try(Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setBirthDate(rs.getDate("birth_date"));
                employee.setDepartmentId(rs.getInt("department_id"));
                employees.add(employee);
            }
        }
        return employees;
    }

    @Override
    public void addEmpl(Employee employee) throws SQLException {

        String sql = "INSERT INTO employees(name,email,salary,birth_date,department_id) VALUES (?,?,?,?,?)";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement  = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            Double salary =employee.getSalary();
            if(salary!=null)preparedStatement.setDouble(3, salary);
            else preparedStatement.setNull(3, Types.DOUBLE);
            preparedStatement.setDate(4, new java.sql.Date(employee.getBirthDate().getTime()));
            preparedStatement.setInt(5, employee.getDepartmentId());
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public void delEmpl(Integer id) throws SQLException {
        String sql="DELETE FROM employees WHERE id=(?)";
        try(
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateEmpl(Employee employee) throws SQLException {

        String sql = "UPDATE employees SET name=(?), email=(?), salary =(?), birth_date = (?), department_id = (?) WHERE id=(?)";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement  = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            Double salary =employee.getSalary();
            if(salary!=null)preparedStatement.setDouble(3, salary);
            else preparedStatement.setNull(3, Types.DOUBLE);
            preparedStatement.setDate(4, new java.sql.Date(employee.getBirthDate().getTime()));
            preparedStatement.setInt(5, employee.getDepartmentId());
            preparedStatement.setInt(6, employee.getId());
            preparedStatement.executeUpdate();
        }
    }

}
