package testtask.controller.employees;

import testtask.dao.EmployeeDao;
import testtask.dao.impl.EmployeeDaoImpl;
import testtask.model.Employee;
import testtask.util.validation.OvalValidator;
import testtask.util.validation.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@WebServlet("/employeeSave")
public class EmployeeSaveController extends HttpServlet {

    private EmployeeDao employeeDao = new EmployeeDaoImpl();

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
                employee.setSalary(Integer.valueOf(employeeSalary));
                if (!employeeBirthDate.equals(""))employee.setBirthDate((new SimpleDateFormat("yyyy-mm-dd")).parse(employeeBirthDate));
                validator.validate(employee);
                employeeDao.addEmpl(employee);
            }else {
                employee= employeeDao.getById(Integer.valueOf(employeeId));
                employee.setName(employeeName);
                employee.setEmail(employeeEmail);
                if(!employeeSalary.equals(""))employee.setSalary(Integer.valueOf(employeeSalary));
                if (!employeeBirthDate.equals(""))employee.setBirthDate((new SimpleDateFormat("yyyy-mm-dd")).parse(employeeBirthDate));
                validator.validate(employee);
                employeeDao.updateEmpl(employee);
            }
            response.sendRedirect("/employees?departmentId="+departmentId);
        }catch (SQLException e) {
            //throw new ServletException("Cannot save employee to DB", e);
            response.sendRedirect("/error");
        } catch (ParseException e) {
            //e.printStackTrace();
            response.sendRedirect("/error");
        }catch (ValidationException exception){
            Map<String,String> map = exception.getMapError();
            request.setAttribute("errors", map);
            request.setAttribute("employee", employee);
            request.getRequestDispatcher("WEB-INF/pages/employees/update.jsp").forward(request,response);
        }/*catch (RuntimeException e){
            response.sendRedirect("/error");
        }*/ catch (Exception e){
            response.sendRedirect("/error");
        }
    }
}