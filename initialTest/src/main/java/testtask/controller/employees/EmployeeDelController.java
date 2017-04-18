/*package testtask.controller.employees;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import testtask.controller.factory.PageController;
import testtask.exception.DAOException;
import testtask.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller(value = "/empDelete")
public class EmployeeDelController implements PageController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
        Integer departmentId = employeeService.getById(employeeId).getDepartment().getId();
        employeeService.delEmpl(employeeId);
        response.sendRedirect("/employees?departmentId=" + departmentId);
    }
}*/