package testtask.service.impl;


import testtask.dao.EmployeeDao;
import testtask.dao.impl.EmployeeDaoImpl;
import testtask.model.Employee;
import testtask.service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public Employee getById(Integer id) throws SQLException {
        return employeeDao.findById(id);
    }

    @Override
    public List<Employee> getAll(Integer id) throws SQLException {
        return employeeDao.findAll(id);
    }

    @Override
    public void addEmpl(Employee employee) throws SQLException {
        employeeDao.addEmpl(employee);
    }

    @Override
    public void delEmpl(Integer id) throws SQLException {
        employeeDao.delEmpl(id);
    }

    @Override
    public void updateEmpl(Employee employee) throws SQLException {
        employeeDao.updateEmpl(employee);
    }

    @Override
    public Employee getByEmail(String s) throws SQLException {
        return employeeDao.findByEmail(s);
    }
}
