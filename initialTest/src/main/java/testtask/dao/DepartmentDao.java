package testtask.dao;


import testtask.model.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDao {

    Department findById(Integer id) throws SQLException;

    Department findByName(String name) throws SQLException;

    List<Department> findAll() throws SQLException;

    void addDep(Department department)throws SQLException;

    void delDep(Integer id)throws SQLException;

    void updateDep(Department department)throws SQLException;

}
