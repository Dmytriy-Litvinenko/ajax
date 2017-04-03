package testtask.controller.employees;

import testtask.dao.EmployeeDao;
import testtask.dao.impl.EmployeeDaoImpl;
import testtask.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/employees")
public class EmployeesAllController extends HttpServlet {

    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer departmentId= Integer.valueOf(request.getParameter("departmentId"));
            List<Employee> employees = employeeDao.getAll(departmentId);
            request.setAttribute("employees", employees);
            request.setAttribute("departmentId", departmentId);
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/pages/employees/all.jsp");
            view.forward(request, response);
        } catch (SQLException e) {
            //throw new ServletException("Cannot obtain employees from DB", e);
            response.sendRedirect("/error");
        }catch (Exception e){
            response.sendRedirect("/error");
        }
    }
}