package testtask.controller.departments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import testtask.exception.DAOException;
import testtask.model.Department;
import testtask.service.impl.DepartmentServiceImpl;

import java.util.List;

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
    public String deleteDepartment( @RequestParam(required = true) Integer departmentId) throws DAOException {
        //Integer departmentId = Integer.parseInt(request.getParameter(""));
        // HttpServletRequest request, HttpServletResponse response
        departmentService.delDep(departmentId);
        //response.sendRedirect("");
        return "redirect:/departments";
    }


   /* public String deleteContact() {
        contactService.remove(id);
        return "redirect:/";
    }*/
}
