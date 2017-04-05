package testtask.controller.departments;


import testtask.controller.factory.Controller;
import testtask.service.DepartmentService;
import testtask.service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepartmentDelController implements Controller {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
            departmentService.delDep(departmentId);
            response.sendRedirect("/departments");
        }catch (Exception e){
            e.printStackTrace(System.out);
            response.sendRedirect("/error");
        }

    }
}
