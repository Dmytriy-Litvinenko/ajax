package testtask.controller.departments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import testtask.exception.DAOException;
import testtask.exception.ValidationException;
import testtask.model.Department;
import testtask.service.impl.DepartmentServiceImpl;

import java.util.List;
import java.util.Map;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public ModelAndView showDepartments() throws DAOException {
        List<Department> departments = departmentService.getAll();
        ModelAndView modelAndView = new ModelAndView("/departments/all");
        modelAndView.addObject("departments", departments);
        return modelAndView;
    }

    @RequestMapping(value = "/depDelete", method = RequestMethod.GET)//
    public String deleteDepartment(@RequestParam(required = true) Integer departmentId) throws DAOException {
        departmentService.delDep(departmentId);
        return "redirect:/departments";
    }

    @RequestMapping(value = "/depUpdate", method = RequestMethod.POST)//
    public ModelAndView updateDepartment(@RequestParam(required = false) Integer departmentId) throws DAOException {

        Department department;
        //String departmentId = request.getParameter("departmentId");
        if (departmentId == null) department = new Department();
        else department = departmentService.getById(departmentId);
        //StringFormatter.stringToInteger(departmentId)//Integer.valueOf(departmentId)
        ModelAndView modelAndView = new ModelAndView("departments/update");
        modelAndView.addObject("department", department);
        return modelAndView;
        //request.getRequestDispatcher("WEB-INF/pages/departments/update.jsp").forward(request, response);
    }


    @RequestMapping(value = "/depSave", method = RequestMethod.POST)//
    public ModelAndView saveDepartment(@RequestParam(required = false) Integer departmentId,
                                       @RequestParam(required = true) String departmentName) throws DAOException {

        ModelAndView modelAndView = new ModelAndView("/departments/all");

        Department department = new Department();
        department.setName(departmentName);
                /*String departmentId = request.getParameter("departmentId");
                String departmentName = request.getParameter("departmentName");
                || departmentId.equals("")
                StringFormatter.stringToInteger()//Integer.valueOf(departmentId));*/
        try {
            if (departmentId == null) {
                departmentService.addDep(department);
            } else {
                department.setId(departmentId);
                departmentService.updateDep(department);
            }
            //response.sendRedirect("/departments");
        } catch (ValidationException exception) {
            modelAndView = new ModelAndView("/departments/update");
            Map<String, String> map = exception.getMapError();
            modelAndView.addObject("errors", map);
            modelAndView.addObject("department", department);
            //request.setAttribute();
            //request.setAttribute();
            //request.getRequestDispatcher("WEB-INF/pages/departments/update.jsp").forward(request, response);

        }
        return modelAndView;


    }


}



   /* public String deleteContact() {
        contactService.remove(id);
        return "redirect:/";
    }*/

