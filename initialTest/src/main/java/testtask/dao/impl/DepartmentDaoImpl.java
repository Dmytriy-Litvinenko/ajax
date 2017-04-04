package testtask.dao.impl;

import testtask.util.db.DatabaseConnection;
import testtask.dao.DepartmentDao;
import testtask.dao.EmployeeDao;
import testtask.model.Department;
import testtask.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public Department findById(Integer id) throws SQLException {

        Department department = new Department();
        String sql = "SELECT * FROM departments WHERE id=?";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery();) {
                while (rs.next()) {
                    department.setId(rs.getInt("id"));
                    department.setName(rs.getString("name"));
                }
            }
        }
        return department;
    }

    @Override
    public Department findByName(String name) throws SQLException {

        Department department = new Department();
        String sql = "SELECT * FROM departments WHERE name=?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            try (ResultSet rs = preparedStatement.executeQuery();) {
                while (rs.next()) {
                    department.setId(rs.getInt("id"));
                    department.setName(rs.getString("name"));
                }
            }
        }
        return department;
    }

    @Override
    public List<Department> findAll() throws SQLException {

        List<Department> departments = new ArrayList<Department>();
        String sql ="SELECT * FROM departments";
        try(Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                departments.add(department);
            }
        }
        return departments;
    }

    @Override
    public void addDep(Department department) throws SQLException {

        String sql = "INSERT INTO departments(name) VALUES (?)";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement  = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delDep(Integer id) throws SQLException {

        List<Employee> employees = employeeDao.findAll(id);
        for (Employee employee:employees) {
            employeeDao.delEmpl(employee.getId());
        }
        String sql="DELETE FROM departments WHERE id=(?)";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateDep(Department department) throws SQLException {

        String sql = "UPDATE departments SET name=(?) WHERE id=(?)";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement  = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getId());
            preparedStatement.executeUpdate();
        }
    }
}
