package testtask.controller.employees;


import testtask.controller.factory.Controller;
import testtask.exception.DAOException;
import testtask.service.EmployeeService;
import testtask.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeDelController implements Controller {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
        Integer departmentId = employeeService.getById(employeeId).getDepartment().getId();
        employeeService.delEmpl(employeeId);
        response.sendRedirect("/employees?departmentId=" + departmentId);
    }
}