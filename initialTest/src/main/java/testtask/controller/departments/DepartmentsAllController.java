package testtask.controller.departments;

import testtask.controller.factory.Controller;
import testtask.exception.DAOException;
import testtask.model.Department;
import testtask.service.DepartmentService;
import testtask.service.impl.DepartmentServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DepartmentsAllController implements Controller {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        List<Department> departments = departmentService.getAll();
        request.setAttribute("departments", departments); // Will be available as ${departments} in JSP
        request.setAttribute("dep", departments.get(0));
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/pages/departments/all.jsp");
        view.forward(request, response);

    }
}
