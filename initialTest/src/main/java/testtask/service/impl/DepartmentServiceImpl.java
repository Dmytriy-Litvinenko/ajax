package testtask.service.impl;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testtask.dao.DepartmentDao;

import testtask.dao.implSpringWithHibernate.DepartmentDaoImpl;
import testtask.exception.DAOException;
import testtask.exception.ValidationException;
import testtask.model.Department;
import testtask.service.DepartmentService;
import testtask.util.validation.OvalValidator;

import java.sql.SQLException;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDaoImpl departmentDao;//Impl = new DepartmentDaoImpl(sessionFactory);

    private OvalValidator validator = new OvalValidator();

    @Override
    public Department getById(Integer id) throws DAOException {
        return departmentDao.findById(id);
    }

    @Override
    public Department getByName(String name) throws DAOException {
        return departmentDao.findByName(name);
    }

    @Override
    public List<Department> getAll() throws DAOException {
        return departmentDao.findAll();
    }

    @Override
    public void addDep(Department department) throws DAOException, ValidationException {
        validator.validate(department);
        departmentDao.addDep(department);
    }

    @Override
    public void delDep(Integer id) throws DAOException {
        departmentDao.delDep(id);
    }

    @Override
    public void updateDep(Department department) throws DAOException, ValidationException {
        validator.validate(department);
        departmentDao.updateDep(department);
    }
}
