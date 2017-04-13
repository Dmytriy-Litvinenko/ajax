package testtask.controller.employees;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import testtask.controller.factory.PageController;
import testtask.exception.DAOException;
import testtask.exception.ValidationException;
import testtask.model.Employee;
import testtask.service.DepartmentService;
import testtask.service.EmployeeService;
import testtask.service.impl.DepartmentServiceImpl;
import testtask.service.impl.EmployeeServiceImpl;
import testtask.util.db.StringFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;
@Controller(value = "/employeeSave")
public class EmployeeSaveController implements PageController {//extends HttpServlet {

    //Log logger = new Log("log.txt");
    //logger.
    //log.logger=
    private static Logger log = Logger.getLogger(EmployeeSaveController.class.getName());

    @Autowired
    private DepartmentServiceImpl departmentService;// = new DepartmentServiceImpl();

    @Autowired
    private EmployeeServiceImpl employeeService;// = new EmployeeServiceImpl();

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        Employee employee = new Employee();//
        String departmentId = request.getParameter("departmentId");
        String employeeId = request.getParameter("employeeId");
        String employeeName = request.getParameter("employeeName");
        String employeeEmail = request.getParameter("employeeEmail");
        String employeeSalary = request.getParameter("employeeSalary");
        String employeeBirthDate = request.getParameter("employeeBirthDate");

        employee.setName(employeeName);
        employee.setEmail(employeeEmail);
        employee.setSalary(StringFormatter.stringToDouble(employeeSalary));
        employee.setBirthDate(StringFormatter.stringToDate(employeeBirthDate));
        employee.setDepartment(departmentService.getById(StringFormatter.stringToInteger(departmentId)));
        try {
            if (employeeId == null || employeeId.equals("")) {
                employeeService.addEmpl(employee);
            } else {
                employee.setId(StringFormatter.stringToInteger(employeeId));
                employeeService.updateEmpl(employee);
            }
            response.sendRedirect("/employees?departmentId=" + departmentId);
        } catch (ValidationException exception) {
            Map<String, String> map = exception.getMapError();
            request.setAttribute("errors", map);
            request.setAttribute("departmentId", departmentId);
            request.setAttribute("employee", employee);
            request.getRequestDispatcher("WEB-INF/pages/employees/update.jsp").forward(request, response);
        }
    }
}