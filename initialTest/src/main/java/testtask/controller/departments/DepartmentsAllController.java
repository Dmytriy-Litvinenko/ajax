package testtask.controller.departments;

import testtask.dao.DepartmentDao;
import testtask.dao.impl.DepartmentDaoImpl;
import testtask.model.Department;
import testtask.service.DepartmentService;
import testtask.service.impl.DepartmentServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DepartmentsAllController extends HttpServlet{

    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Department> departments = departmentService.getAll();
            request.setAttribute("departments", departments); // Will be available as ${departments} in JSP
            request.setAttribute("dep", departments.get(0));
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/pages/departments/all.jsp");
            view.forward(request, response);
        }catch (Exception e){
            response.sendRedirect("/error");
        }
    }
}
