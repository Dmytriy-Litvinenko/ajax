package testtask.controller.employees;


import testtask.dao.EmployeeDao;
import testtask.dao.impl.EmployeeDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/empDelete")
public class EmployeeDelController extends HttpServlet {

    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
            Integer departmentId = employeeDao.getById(employeeId).getDepartmentId();
            employeeDao.delEmpl(employeeId);
            response.sendRedirect("/employees?departmentId="+departmentId);
        }catch (SQLException e){
            //throw new ServletException("Cannot delete department from DB", e);
            response.sendRedirect("/error");
        }catch (Exception e){
            response.sendRedirect("/error");
        }

    }
}