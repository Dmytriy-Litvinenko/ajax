package testtask.controller.employees;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import testtask.controller.factory.PageController;
import testtask.exception.DAOException;
import testtask.model.Employee;
import testtask.service.EmployeeService;
import testtask.service.impl.DepartmentServiceImpl;
import testtask.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller(value = "/empDelete")
public class EmployeeDelController implements PageController {

    @Autowired
    private EmployeeServiceImpl employeeService;// = new EmployeeServiceImpl();

    @Autowired
    private DepartmentServiceImpl departmentService;

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
        Integer departmentId = employeeService.getById(employeeId).getDepartment().getId();
        //Employee employee=employeeService.getById(employeeId);
        employeeService.delEmpl(employeeId);
        //departmentService.updateDep(employee.getDepartment());
        response.sendRedirect("/employees?departmentId=" + departmentId);
    }
}