package testtask.dao;

import testtask.exception.DAOException;
import testtask.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {

    Employee findById(Integer id) throws DAOException;

    List<Employee> findAll(Integer id) throws DAOException;

    void addEmpl(Employee employee) throws DAOException;

    void delEmpl(Integer id) throws DAOException;

    void updateEmpl(Employee employee) throws DAOException;

    Employee findByEmail(String s) throws DAOException;
}
