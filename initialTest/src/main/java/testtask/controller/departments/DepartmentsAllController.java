package testtask.controller.departments;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import org.springframework.web.HttpRequestHandler;
import testtask.controller.factory.PageController;
import testtask.exception.DAOException;
import testtask.model.Department;
import testtask.service.DepartmentService;
import testtask.service.impl.DepartmentServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@Component("feed")
public class DepartmentsAllController implements PageController {

    //@Autowired
    private DepartmentService departmentService = new DepartmentServiceImpl();

    //@Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        List<Department> departments = departmentService.getAll();
        request.setAttribute("departments", departments);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/pages/departments/all.jsp");
        view.forward(request, response);

    }

   /* @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departments = null;
        try {
            departments = departmentService.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        request.setAttribute("departments", departments);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/pages/departments/all.jsp");
        view.forward(request, response);
    }*/
}
