package testtask.dao;

import testtask.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {

        Employee getById(Integer id)throws SQLException;

        List<Employee> getAll(Integer id)throws SQLException;

        void addEmpl(Employee employee)throws SQLException;

        void delEmpl(Integer id)throws SQLException;

        void updateEmpl(Employee employee)throws SQLException;



}
