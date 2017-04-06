package testtask.controller.employees;


import testtask.controller.factory.Controller;
import testtask.dao.EmployeeDao;
import testtask.dao.impl.EmployeeDaoImpl;
import testtask.exception.DAOException;
import testtask.service.EmployeeService;
import testtask.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EmployeeDelController implements Controller{

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //try {
            Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
            Integer departmentId = employeeService.getById(employeeId).getDepartmentId();
            employeeService.delEmpl(employeeId);
            response.sendRedirect("/employees?departmentId="+departmentId);
        /*}catch (Exception e){
            e.printStackTrace(System.out);
            response.sendRedirect("/error");
        }*/

    }
}