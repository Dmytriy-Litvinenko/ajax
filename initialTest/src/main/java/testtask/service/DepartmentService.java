package testtask.service;

import testtask.exception.DAOException;
import testtask.model.Department;

import java.util.List;


public interface DepartmentService {

    Department getById(Integer id) throws DAOException;

    Department getByName(String name) throws DAOException;

    List<Department> getAll() throws DAOException;

    void addDep(Department department) throws DAOException;

    void delDep(Integer id) throws DAOException;

    void updateDep(Department department) throws DAOException;
}
