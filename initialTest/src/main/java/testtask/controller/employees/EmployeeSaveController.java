package testtask.controller.employees;

import testtask.controller.factory.Controller;
import testtask.exception.DAOException;
import testtask.model.Employee;
import testtask.service.DepartmentService;
import testtask.service.EmployeeService;
import testtask.service.impl.DepartmentServiceImpl;
import testtask.service.impl.EmployeeServiceImpl;
import testtask.util.db.StringFormatter;
import testtask.util.validation.OvalValidator;
import testtask.exception.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;
import java.util.logging.Logger;

public class EmployeeSaveController implements Controller {//extends HttpServlet {

    //Log logger = new Log("log.txt");
    //logger.
    //log.logger=
    private static Logger log = Logger.getLogger(EmployeeSaveController.class.getName());

    private DepartmentService departmentService = new DepartmentServiceImpl();

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        Employee employee = null;//
        String departmentId = request.getParameter("departmentId");
        String employeeId = request.getParameter("employeeId");
        String employeeName = request.getParameter("employeeName");
        String employeeEmail = request.getParameter("employeeEmail");
        String employeeSalary = request.getParameter("employeeSalary");
        String employeeBirthDate = request.getParameter("employeeBirthDate");
        OvalValidator validator = new OvalValidator();

        try {
            if (employeeId == null || employeeId.equals("")) {
                employee = new Employee();
                //employee.setDepartmentId(StringFormatter.stringToInteger(departmentId));
                employee.setDepartment(departmentService.getById(StringFormatter.stringToInteger(departmentId)));
                employee.setName(employeeName);
                employee.setEmail(employeeEmail);
                employee.setSalary(StringFormatter.stringToDouble(employeeSalary));
                employee.setBirthDate(StringFormatter.stringToDate(employeeBirthDate));
                validator.validate(employee);
                employeeService.addEmpl(employee);
            } else {
                employee = employeeService.getById(StringFormatter.stringToInteger(employeeId));
                employee.setName(employeeName);
                employee.setEmail(employeeEmail);
                employee.setSalary(StringFormatter.stringToDouble(employeeSalary));
                employee.setBirthDate(StringFormatter.stringToDate(employeeBirthDate));
                validator.validate(employee);
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