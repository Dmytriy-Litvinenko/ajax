package testtask.controller.departments;

import testtask.dao.DepartmentDao;
import testtask.dao.impl.DepartmentDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DepartmentDelController extends HttpServlet {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
            departmentDao.delDep(departmentId);
            response.sendRedirect("/departments");

        }catch (SQLException e){
            throw new ServletException("Cannot delete department from DB", e);
        }

    }
}
