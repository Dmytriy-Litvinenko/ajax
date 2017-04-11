package testtask.controller.departments;



import testtask.controller.factory.PageController;
import testtask.exception.DAOException;
import testtask.service.DepartmentService;
import testtask.service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepartmentDelController implements PageController {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DAOException {
        Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
        departmentService.delDep(departmentId);
        response.sendRedirect("/departments");
    }
}
