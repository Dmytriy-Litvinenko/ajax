package testtask.service.impl;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testtask.dao.DepartmentDao;

import testtask.dao.implSpringWithHibernate.DepartmentDaoImpl;
import testtask.exception.DAOException;
import testtask.exception.ValidationException;
import testtask.model.Department;
import testtask.service.DepartmentService;
import testtask.util.validation.OvalValidator;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Service
//@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDaoImpl departmentDao; //= new DepartmentDaoImpl(sessionFactory);

    @Autowired
    private OvalValidator validator; //= new OvalValidator();

    @Override
    @Transactional
    public Department getById(Integer id) throws DAOException {
        return departmentDao.findById(id);

    }

    @Override
    //@Transactional
    public Department getByName(String name) throws DAOException {
        return departmentDao.findByName(name);
    }

    @Override
    @Transactional
    public List<Department> getAll() throws DAOException {
        return departmentDao.findAll();
    }

    @Override
    @Transactional
    public void addDep(Department department) throws DAOException, ValidationException {
        validator.validate(department);
        departmentDao.addDep(department);
    }

    @Override
    @Transactional
    public void delDep(Integer id) throws DAOException {
        departmentDao.delDep(id);
    }

    @Override
    @Transactional
    public void updateDep(Department department) throws DAOException, ValidationException {
        validator.validate(department);
        departmentDao.updateDep(department);
    }
}
