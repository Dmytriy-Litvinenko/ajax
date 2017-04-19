package testtask.controller.departments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        //throw new DAOException();
        //throw new RuntimeException();
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
        if (departmentId == null) department = new Department();
        else department = departmentService.getById(departmentId);
        ModelAndView modelAndView = new ModelAndView("departments/update");
        modelAndView.addObject("department", department);
        return modelAndView;
    }


    @RequestMapping(value = "/depSave", method = RequestMethod.POST)//
    public String saveDepartment(@RequestParam(required = false) Integer departmentId,
                                 @RequestParam(required = true) String departmentName, Model model) throws DAOException {
        Department department = new Department();
        department.setName(departmentName);
        try {
            if (departmentId == null) {
                departmentService.addDep(department);
            } else {
                department.setId(departmentId);
                departmentService.updateDep(department);
            }
        } catch (ValidationException exception) {
            Map<String, String> map = exception.getMapError();
            model.addAttribute("errors", map);
            model.addAttribute("department", department);
            return "departments/update";
        }
        return "redirect:/departments";
    }
}
