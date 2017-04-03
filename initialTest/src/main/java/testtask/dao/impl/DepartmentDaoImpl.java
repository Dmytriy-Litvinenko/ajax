package testtask.dao.impl;

import testtask.dao.DatabaseConnection;
import testtask.dao.DepartmentDao;
import testtask.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    public Department getById(Integer id) throws SQLException {

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

    public Department getByName(String name) throws SQLException {

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

    public List<Department> getAll() throws SQLException {

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
        /*if (rs!=null)rs.close();
        if (statement!=null)statement.close();
        if (connection!=null)connection.close();*/
        return departments;
    }

    public void addDep(Department department) throws SQLException {

        String sql = "INSERT INTO departments(name) VALUES (?)";

        try(Connection connection = DatabaseConnection.getConnection();

        PreparedStatement preparedStatement  = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.executeUpdate();
        }
        /*if (preparedStatement!=null)preparedStatement.close();
        if (connection!=null)connection.close();*/

    }

    public void delDep(Integer id) throws SQLException {

        String sql="DELETE FROM departments WHERE id=(?)";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        /*if (preparedStatement!=null)preparedStatement.close();
        if (connection!=null)connection.close();*/
    }

    public void updateDep(Department department) throws SQLException {

        String sql = "UPDATE departments SET name=(?) WHERE id=(?)";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement  = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getId());
            preparedStatement.executeUpdate();
        }
        /*if (preparedStatement!=null)preparedStatement.close();
        if (connection!=null)connection.close();*/

    }
}
