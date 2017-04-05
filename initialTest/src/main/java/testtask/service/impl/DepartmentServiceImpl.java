package testtask.service.impl;


import testtask.dao.DepartmentDao;
import testtask.dao.impl.DepartmentDaoImpl;
import testtask.model.Department;
import testtask.service.DepartmentService;

import java.sql.SQLException;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public Department getById(Integer id) throws SQLException {
        return departmentDao.findById(id);
    }

    @Override
    public Department getByName(String name) throws SQLException {
        return departmentDao.findByName(name);
    }

    @Override
    public List<Department> getAll() throws SQLException {
        return departmentDao.findAll();
    }

    @Override
    public void addDep(Department department) throws SQLException {
        departmentDao.addDep(department);
    }

    @Override
    public void delDep(Integer id) throws SQLException {
        departmentDao.delDep(id);
    }

    @Override
    public void updateDep(Department department) throws SQLException {
        departmentDao.updateDep(department);
    }
}
