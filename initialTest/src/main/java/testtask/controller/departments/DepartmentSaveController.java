package testtask.controller.departments;


import testtask.controller.factory.PageController;
import testtask.exception.DAOException;
import testtask.exception.ValidationException;
import testtask.model.Department;
import testtask.service.DepartmentService;
import testtask.service.impl.DepartmentServiceImpl;
import testtask.util.db.StringFormatter;
import testtask.util.validation.OvalValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class DepartmentSaveController implements PageController {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        Department department = null;
        String departmentId = request.getParameter("departmentId");
        String departmentName = request.getParameter("departmentName");
        //OvalValidator validator = new OvalValidator();

        try {
            if (departmentId == null || departmentId.equals("")) {
                department = new Department();
                department.setName(departmentName);
                //validator.validate(department);
                departmentService.addDep(department);
            } else {
                department = departmentService.getById(StringFormatter.stringToInteger(departmentId));//Integer.valueOf(departmentId));
                department.setName(departmentName);
                //validator.validate(department);
                departmentService.updateDep(department);
            }
            response.sendRedirect("/departments");
        } catch (ValidationException exception) {
            Map<String, String> map = exception.getMapError();
            request.setAttribute("errors", map);
            request.setAttribute("department", department);
            request.getRequestDispatcher("WEB-INF/pages/departments/update.jsp").forward(request, response);
        }
    }
}

