package testtask.controller.departments;

import testtask.model.Department;
import testtask.service.DepartmentService;
import testtask.service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepartmentUpdateController extends HttpServlet {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Department department = null;
        String departmentId = request.getParameter("departmentId");
        try {
            if(departmentId==null)
                department=new Department();
            else
                department= departmentService.getById(Integer.valueOf(departmentId));

            request.setAttribute("department", department);
            request.getRequestDispatcher("WEB-INF/pages/departments/update.jsp").forward(request,response);
        }catch (Exception e){
            response.sendRedirect("/error");
        }
    }
}










