package testtask.controller.departments;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import testtask.dao.DepartmentDao;
import testtask.dao.impl.DepartmentDaoImpl;
import testtask.model.Department;
import testtask.util.validation.OvalValidator;
import testtask.util.validation.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DepartmentSaveController  extends HttpServlet {

    private DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Department department = null;//
        String departmentId = request.getParameter("departmentId");
        String departmentName = request.getParameter("departmentName");
        OvalValidator validator = new OvalValidator();

        try {
            if(departmentId==null || departmentId.equals("")){
                department=new Department();
                department.setName(departmentName);
                validator.validate(department);
                departmentDao.addDep(department);
            }else {
                department=departmentDao.getById(Integer.valueOf(departmentId));
                department.setName(departmentName);
                validator.validate(department);
                departmentDao.updateDep(department);
            }
            response.sendRedirect("/departments");
        }catch (SQLException e){
            //throw new ServletException("Cannot save department from DB", e);
            response.sendRedirect("/error");
        }catch (ValidationException exception){
            Map<String,String> map = exception.getMapError();
            request.setAttribute("errors", map);
            request.setAttribute("department", department);
            request.getRequestDispatcher("WEB-INF/pages/departments/update.jsp").forward(request,response);
        }
        catch (Exception e){
            response.sendRedirect("/error");
        }
    }
}

