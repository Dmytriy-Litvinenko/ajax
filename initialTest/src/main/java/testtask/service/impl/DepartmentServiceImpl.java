package testtask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import testtask.dao.implSpringWithHibernate.DepartmentDaoImpl;
import testtask.exception.DAOException;
import testtask.exception.ValidationException;
import testtask.model.Department;
import testtask.service.DepartmentService;
import testtask.util.validation.OvalValidator;


import java.util.List;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDaoImpl departmentDao;

    @Autowired
    private OvalValidator validator;

    @Override
    @Transactional(readOnly = true)
    public Department getById(Integer id) throws DAOException {
        return departmentDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Department getByName(String name) throws DAOException {
        return departmentDao.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)//, propagation = Propagation.REQUIRED
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
