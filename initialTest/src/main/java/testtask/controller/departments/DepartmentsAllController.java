package testtask.controller.departments;

import testtask.dao.DepartmentDao;
import testtask.dao.impl.DepartmentDaoImpl;
import testtask.model.Department;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DepartmentsAllController extends HttpServlet{

    private DepartmentDao departmentDao=new DepartmentDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Department> departments = departmentDao.getAll();
            request.setAttribute("departments", departments); // Will be available as ${departments} in JSP
            request.setAttribute("dep", departments.get(0));
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/pages/departments/all.jsp");
            view.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Cannot obtain departments from DB", e);
        }
    }
}
