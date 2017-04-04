package testtask.controller.employees;

import testtask.dao.EmployeeDao;
import testtask.dao.impl.EmployeeDaoImpl;
import testtask.model.Employee;
import testtask.service.EmployeeService;
import testtask.service.impl.EmployeeServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/employees")
public class EmployeesAllController extends HttpServlet {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer departmentId= Integer.valueOf(request.getParameter("departmentId"));
            List<Employee> employees = employeeService.getAll(departmentId);
            request.setAttribute("employees", employees);
            request.setAttribute("departmentId", departmentId);
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/pages/employees/all.jsp");
            view.forward(request, response);
        }catch (Exception e){
            response.sendRedirect("/error");
        }
    }
}