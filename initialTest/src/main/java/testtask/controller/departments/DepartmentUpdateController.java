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

public class DepartmentUpdateController extends HttpServlet {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Department department = null;
        String departmentId = request.getParameter("departmentId");
        try {
            if(departmentId==null)
                department=new Department();
            else
                department=departmentDao.getById(Integer.valueOf(departmentId));

            request.setAttribute("department", department);
            request.getRequestDispatcher("WEB-INF/pages/departments/update.jsp").forward(request,response);
        }catch (SQLException e){
            //throw new ServletException("Cannot update department from DB", e);
            response.sendRedirect("/error");
        }catch (Exception e){
            response.sendRedirect("/error");
        }
    }
}










