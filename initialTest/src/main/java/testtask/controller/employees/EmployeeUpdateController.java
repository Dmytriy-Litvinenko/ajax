package testtask.controller.employees;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import testtask.controller.factory.PageController;
import testtask.exception.DAOException;
import testtask.model.Employee;
import testtask.service.DepartmentService;
import testtask.service.EmployeeService;
import testtask.service.impl.DepartmentServiceImpl;
import testtask.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller(value = "/employeeUpdate")
public class EmployeeUpdateController implements PageController {

    @Autowired
    private DepartmentServiceImpl departmentService;// = new DepartmentServiceImpl();

    @Autowired
    private EmployeeServiceImpl employeeService;// = new EmployeeServiceImpl();

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        Employee employee = null;
        String departmentId = request.getParameter("departmentId");
        String employeeId = request.getParameter("employeeId");
        if (employeeId == null || employeeId.equals("")) {
            employee = new Employee();
            //employee.setDepartmentId(Integer.valueOf(departmentId));
            employee.setDepartment(departmentService.getById(Integer.valueOf(departmentId)));
        } else {
            employee = employeeService.getById(Integer.valueOf(employeeId));
            //departmentId = employee.getDepartmentId().toString();
            departmentId = employee.getDepartment().getId().toString();
        }
        request.setAttribute("employee", employee);
        request.setAttribute("departmentId", departmentId);
        request.getRequestDispatcher("WEB-INF/pages/employees/update.jsp").forward(request, response);
    }
}










