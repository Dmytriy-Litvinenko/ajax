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
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM departments WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            department.setId(rs.getInt("id"));
            department.setName(rs.getString("name"));
        }
        if (rs!=null)rs.close();
        if (preparedStatement!=null)preparedStatement.close();
        if (connection!=null)connection.close();
        return department;
    }


    public List<Department> getAll() throws SQLException {

        List<Department> departments = new ArrayList<Department>();
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from departments");
        while (rs.next()) {
            Department department = new Department();
            department.setId(rs.getInt("id"));
            department.setName(rs.getString("name"));
            departments.add(department);
        }
        if (rs!=null)rs.close();
        if (statement!=null)statement.close();
        if (connection!=null)connection.close();
        return departments;
    }

    public void addDep(Department department) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO departments(name) VALUES (?)";
        PreparedStatement preparedStatement  = connection.prepareStatement(sql);
        preparedStatement.setString(1, department.getName());
        preparedStatement.executeUpdate();
        if (preparedStatement!=null)preparedStatement.close();
        if (connection!=null)connection.close();

    }

    public void delDep(Integer id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql="DELETE FROM departments WHERE id=(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        if (preparedStatement!=null)preparedStatement.close();
        if (connection!=null)connection.close();
    }

    public void updateDep(Department department) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE departments SET name=(?) WHERE id=(?)";
        PreparedStatement preparedStatement  = connection.prepareStatement(sql);
        preparedStatement.setString(1, department.getName());
        preparedStatement.setInt(2, department.getId());
        preparedStatement.executeUpdate();
        if (preparedStatement!=null)preparedStatement.close();
        if (connection!=null)connection.close();

    }
}
