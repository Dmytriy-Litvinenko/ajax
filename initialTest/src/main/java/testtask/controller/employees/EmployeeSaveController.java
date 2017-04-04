package testtask.controller.employees;

import testtask.model.Employee;
import testtask.service.EmployeeService;
import testtask.service.impl.EmployeeServiceImpl;
import testtask.util.db.StringFormatter;
import testtask.util.validation.OvalValidator;
import testtask.util.validation.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/employeeSave")
public class EmployeeSaveController extends HttpServlet {

    //Log logger = new Log("log.txt");
    //logger.
    //log.logger=
    private static Logger log = Logger.getLogger(EmployeeSaveController.class.getName());

    private EmployeeService employeeService = new EmployeeServiceImpl();

    public EmployeeSaveController() throws IOException {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

        Employee employee = null;//
        String departmentId = request.getParameter("departmentId");
        String employeeId = request.getParameter("employeeId");
        String employeeName = request.getParameter("employeeName");
        String employeeEmail= request.getParameter("employeeEmail");
        String employeeSalary= request.getParameter("employeeSalary");
        String employeeBirthDate = request.getParameter("employeeBirthDate");
        OvalValidator validator = new OvalValidator();

        try {
            if(employeeId==null || employeeId.equals("")){
                employee=new Employee();
                employee.setName(employeeName);
                employee.setDepartmentId(Integer.valueOf(departmentId));
                employee.setEmail(employeeEmail);
                if(employeeSalary.equals(""))employee.setSalary(null);
                else employee.setSalary(Integer.valueOf(employeeSalary));
                if (!employeeBirthDate.equals(""))
                    employee.setBirthDate((new SimpleDateFormat("yyyy-mm-dd")).parse(employeeBirthDate));
                else employee.setBirthDate(null);
                validator.validate(employee);
                employeeService.addEmpl(employee);
            }else {
                employee= employeeService.getById(Integer.valueOf(employeeId));
                employee.setName(employeeName);
                employee.setEmail(employeeEmail);
                employee.setSalary(StringFormatter.stringToInteger(employeeSalary));
                employee.setBirthDate(StringFormatter.stringToDate(employeeBirthDate));
                validator.validate(employee);
                employeeService.updateEmpl(employee);
            }
            response.sendRedirect("/employees?departmentId="+departmentId);
        }catch (ValidationException exception){
            Map<String,String> map = exception.getMapError();
            request.setAttribute("errors", map);
            request.setAttribute("departmentId", departmentId);
            request.setAttribute("employee", employee);
            request.getRequestDispatcher("WEB-INF/pages/employees/update.jsp").forward(request,response);
        }catch (Exception e){
            //e.printStackTrace();
            //log.log(Level.SEVERE,e.getMessage(),e);
            //log.

            response.sendRedirect("/error");
        }
    }
}