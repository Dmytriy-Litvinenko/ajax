package testtask.controller.employees;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import testtask.controller.factory.PageController;
import testtask.exception.DAOException;
import testtask.model.Employee;
import testtask.service.EmployeeService;
import testtask.service.impl.EmployeeServiceImpl;
import testtask.util.db.StringFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller(value = "/employees")
public class EmployeesAllController implements PageController {

    @Autowired
    private EmployeeServiceImpl employeeService;// = new EmployeeServiceImpl();

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        String depId = request.getParameter("departmentId");
        Integer departmentId = StringFormatter.stringToInteger(depId);
        if (departmentId == null) {
            request.getRequestDispatcher("/WEB-INF/pages/errorPage.jsp").forward(request, response);
        } else {
            List<Employee> employees = employeeService.getAll(departmentId);
            request.setAttribute("employees", employees);
            request.setAttribute("departmentId", departmentId);
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/pages/employees/all.jsp");
            view.forward(request, response);
        }
    }
}