package testtask.dao;


import testtask.exception.DAOException;
import testtask.model.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDao {

    Department findById(Integer id) throws DAOException;

    Department findByName(String name) throws DAOException;

    List<Department> findAll() throws DAOException;

    void addDep(Department department)throws DAOException;

    void delDep(Integer id)throws DAOException;

    void updateDep(Department department)throws DAOException;

}
