package testtask.dao.impl;

import testtask.dao.DatabaseConnection;
import testtask.dao.EmployeeDao;
import testtask.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    public Employee getById(Integer id) throws SQLException {


        Employee employee = new Employee();
        String sql = "SELECT * FROM employees WHERE id=?";
        //try(Connection connection = DatabaseConnection.getConnection()) {
          //  try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setEmail(rs.getString("email"));
            employee.setSalary(rs.getInt("salary"));
            employee.setBirthDate(rs.getDate("birth_date"));
            employee.setDepartmentId(rs.getInt("department_id"));
        }//}}

        if (rs!=null)rs.close();
        if (preparedStatement!=null)preparedStatement.close();
        if (connection!=null)connection.close();

        return employee;
    }


    public List<Employee> getAll( Integer departmentId) throws SQLException {

        List<Employee> employees = new ArrayList<Employee>();
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        String sql ="SELECT id,name,email,salary,birth_date,department_id FROM employees WHERE department_id="+departmentId;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setEmail(rs.getString("email"));
            employee.setSalary(rs.getInt("salary"));
            employee.setBirthDate(rs.getDate("birth_date"));
            employee.setDepartmentId(rs.getInt("department_id"));
            employees.add(employee);
        }
        if (rs!=null)rs.close();
        if (statement!=null)statement.close();
        if (connection!=null)connection.close();
        return employees;
    }



    public void addEmpl(Employee employee) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO employees(name,email,salary,birth_date,department_id) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement  = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmail());
        preparedStatement.setInt(3, employee.getSalary());
        preparedStatement.setDate(4, new java.sql.Date(employee.getBirthDate().getTime()));
        preparedStatement.setInt(5, employee.getDepartmentId());
        preparedStatement.executeUpdate();
        if (preparedStatement!=null)preparedStatement.close();
        if (connection!=null)connection.close();

    }

    public void delEmpl(Integer id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql="DELETE FROM employees WHERE id=(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        if (preparedStatement!=null)preparedStatement.close();
        if (connection!=null)connection.close();
    }

    public void updateEmpl(Employee employee) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE employees SET name=(?), email=(?), salary =(?), birth_date = (?), department_id = (?) WHERE id=(?)";
        PreparedStatement preparedStatement  = connection.prepareStatement(sql);
        preparedStatement.setString(1, employee.getName());
        preparedStatement.setString(2, employee.getEmail());
        preparedStatement.setInt(3, employee.getSalary());
        java.sql.Date sqlDate = new java.sql.Date(employee.getBirthDate().getTime());
        preparedStatement.setDate(4, sqlDate);
        preparedStatement.setInt(5,employee.getDepartmentId());
        preparedStatement.setInt(6, employee.getId());
        preparedStatement.executeUpdate();
        if (preparedStatement!=null)preparedStatement.close();
        if (connection!=null)connection.close();

    }

}
