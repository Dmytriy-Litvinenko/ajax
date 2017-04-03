package testtask.dao;


import testtask.model.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDao {

    Department getById(Integer id) throws SQLException;

    List<Department> getAll() throws SQLException;

    void addDep(Department department)throws SQLException;

    void delDep(Integer id)throws SQLException;

    void updateDep(Department department)throws SQLException;

}
