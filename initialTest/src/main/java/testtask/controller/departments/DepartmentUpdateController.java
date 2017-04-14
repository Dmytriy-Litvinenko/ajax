package testtask.controller.departments;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import testtask.controller.factory.PageController;
import testtask.exception.DAOException;
import testtask.model.Department;
import testtask.service.DepartmentService;
import testtask.service.impl.DepartmentServiceImpl;
import testtask.util.db.StringFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller(value = "/depUpdate")
public class DepartmentUpdateController implements PageController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        Department department;
        String departmentId = request.getParameter("departmentId");
        if (departmentId == null)
            department = new Department();
        else
            department = departmentService.getById(StringFormatter.stringToInteger(departmentId));//Integer.valueOf(departmentId)
        request.setAttribute("department", department);
        request.getRequestDispatcher("WEB-INF/pages/departments/update.jsp").forward(request, response);
    }
}










