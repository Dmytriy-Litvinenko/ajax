package testtask.service.impl;


import testtask.dao.EmployeeDao;

import testtask.dao.implWithHibernate.EmployeeDaoImpl;
import testtask.exception.DAOException;
import testtask.exception.ValidationException;
import testtask.model.Employee;
import testtask.service.EmployeeService;
import testtask.util.validation.OvalValidator;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    private OvalValidator validator = new OvalValidator();

    @Override
    public Employee getById(Integer id) throws DAOException {
        return employeeDao.findById(id);
    }

    @Override
    public List<Employee> getAll(Integer id) throws DAOException {
        return employeeDao.findAll(id);
    }

    @Override
    public void addEmpl(Employee employee) throws DAOException, ValidationException {
        validator.validate(employee);
        employeeDao.addEmpl(employee);
    }

    @Override
    public void delEmpl(Integer id) throws DAOException {
        employeeDao.delEmpl(id);
    }

    @Override
    public void updateEmpl(Employee employee) throws DAOException, ValidationException {
        validator.validate(employee);
        employeeDao.updateEmpl(employee);
    }

    @Override
    public Employee getByEmail(String s) throws DAOException {
        return employeeDao.findByEmail(s);
    }
}
