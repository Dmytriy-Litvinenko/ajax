package testtask.controller.departments;


import testtask.service.DepartmentService;
import testtask.service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepartmentDelController extends HttpServlet {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
            departmentService.delDep(departmentId);
            response.sendRedirect("/departments");
        }catch (Exception e){
            response.sendRedirect("/error");
        }

    }
}
