package testtask.service;


import testtask.exception.DAOException;
import testtask.exception.ValidationException;
import testtask.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {

    Employee getById(Integer id) throws DAOException;

    List<Employee> getAll(Integer id) throws DAOException;

    void addEmpl(Employee employee) throws DAOException, ValidationException;

    void delEmpl(Integer id) throws DAOException;

    void updateEmpl(Employee employee) throws DAOException, ValidationException;

    Employee getByEmail(String s) throws DAOException;
}
