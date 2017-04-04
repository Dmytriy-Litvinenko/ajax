package testtask.dao;

import testtask.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {

        Employee findById(Integer id)throws SQLException;

        List<Employee> findAll(Integer id)throws SQLException;

        void addEmpl(Employee employee)throws SQLException;

        void delEmpl(Integer id)throws SQLException;

        void updateEmpl(Employee employee)throws SQLException;

        Employee findByEmail(String s)throws SQLException;
}
