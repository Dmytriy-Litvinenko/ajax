package testtask.controller.departments;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import testtask.controller.factory.PageController;
import testtask.exception.DAOException;
import testtask.service.DepartmentService;
import testtask.service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
@Controller(value = "/depDelete")
public class DepartmentDelController implements PageController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    @Override
    public void goToPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DAOException {
        Integer departmentId = Integer.parseInt(request.getParameter("departmentId"));
        departmentService.delDep(departmentId);
        response.sendRedirect("/departments");
    }
}*/
