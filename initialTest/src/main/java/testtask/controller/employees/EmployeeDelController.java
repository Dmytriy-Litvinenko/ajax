package testtask.controller.employees;


import testtask.dao.EmployeeDao;
import testtask.dao.impl.EmployeeDaoImpl;
import testtask.service.EmployeeService;
import testtask.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/empDelete")
public class EmployeeDelController extends HttpServlet {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
            Integer departmentId = employeeService.getById(employeeId).getDepartmentId();
            employeeService.delEmpl(employeeId);
            response.sendRedirect("/employees?departmentId="+departmentId);
        }catch (Exception e){
            response.sendRedirect("/error");
        }

    }
}