package testtask.controller.employees;

import testtask.controller.factory.Controller;
import testtask.exception.DAOException;
import testtask.model.Employee;
import testtask.service.EmployeeService;
import testtask.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EmployeeUpdateController implements Controller {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        Employee employee = null;
        String departmentId = request.getParameter("departmentId");
        String employeeId = request.getParameter("employeeId");
        //try {
        if (employeeId == null || employeeId.equals("")) {
            employee = new Employee();
            employee.setDepartmentId(Integer.valueOf(departmentId));
        } else {
            employee = employeeService.getById(Integer.valueOf(employeeId));

            departmentId = employee.getDepartmentId().toString();
        }

        request.setAttribute("employee", employee);
        request.setAttribute("departmentId", departmentId);
        request.getRequestDispatcher("WEB-INF/pages/employees/update.jsp").forward(request, response);
        /*}catch (Exception e){
            e.printStackTrace(System.out);
            response.sendRedirect("/error");
        }*/
    }
}










