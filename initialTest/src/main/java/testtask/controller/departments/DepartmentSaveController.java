package testtask.controller.departments;

import testtask.dao.DepartmentDao;
import testtask.dao.impl.DepartmentDaoImpl;
import testtask.model.Department;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by user on 31.03.17.
 */
public class DepartmentSaveController  extends HttpServlet {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Department department = null;//
        String departmentId = request.getParameter("departmentId");
        String departmentName = request.getParameter("departmentName");

        try {
            if(departmentId==null || departmentId.equals("")){
                department=new Department();
                department.setName(departmentName);
                departmentDao.addDep(department);
            }else {
                department=departmentDao.getById(Integer.valueOf(departmentId));
                department.setName(departmentName);
                departmentDao.updateDep(department);
            }
            response.sendRedirect("/departments");
        }catch (SQLException e){
            throw new ServletException("Cannot save department from DB", e);
        }
    }
}

