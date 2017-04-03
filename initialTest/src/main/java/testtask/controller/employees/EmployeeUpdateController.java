package testtask.controller.employees;

import testtask.dao.EmployeeDao;
import testtask.dao.impl.EmployeeDaoImpl;
import testtask.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/employeeUpdate")
public class EmployeeUpdateController extends HttpServlet {

    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Employee employee = null;
        String departmentId = request.getParameter("departmentId");
        String employeeId = request.getParameter("employeeId");
        try {
            if(employeeId==null || employeeId.equals("")){
                employee=new Employee();
                employee.setDepartmentId(Integer.valueOf(departmentId));
            }
            else{
                employee= employeeDao.getById(Integer.valueOf(employeeId));
                departmentId=employee.getDepartmentId().toString();
            }

            request.setAttribute("employee", employee);
            request.setAttribute("departmentId", departmentId);
            request.getRequestDispatcher("WEB-INF/pages/employees/update.jsp").forward(request,response);
        }catch (SQLException e){
            throw new ServletException("Cannot update department from DB", e);
        }
    }
}










