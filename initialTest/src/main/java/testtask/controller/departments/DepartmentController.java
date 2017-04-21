package testtask.controller.departments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    public @ResponseBody Department showDepartmentById(@PathVariable Integer id) throws DAOException {
        return departmentService.getById(id);
    }

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public @ResponseBody List<Department> showDepartments() throws DAOException {
        return departmentService.getAll();
    }

    @RequestMapping(value = "/depDelete", method = RequestMethod.GET)
    public @ResponseBody List<Department> deleteDepartment(@RequestParam(required = true) Integer departmentId) throws DAOException {
        departmentService.delDep(departmentId);
        return departmentService.getAll();
    }

    @PostMapping(value = "/depUpdate")
    public @ResponseBody Department updateDepartment(@RequestParam(required = false) Integer departmentId, Model model) throws DAOException {
        Department department;
        if (departmentId == null) department = new Department();
        else department = departmentService.getById(departmentId);
        return department;
    }

    @RequestMapping(value = "/depSave", method = RequestMethod.POST)
    public ModelAndView saveDepartment(Department department) throws DAOException {
        Integer departmentId = department.getId();
        try {
            if (departmentId == null) {
                departmentService.addDep(department);
            } else {
                department.setId(departmentId);
                departmentService.updateDep(department);
            }
        } catch (ValidationException exception) {
            ModelAndView modelAndView = new ModelAndView("/departments/update");
            Map<String, String> map = exception.getMapError();
            modelAndView.addObject("errors", map);
            modelAndView.addObject("department", department);
            return modelAndView;
        }
        return new ModelAndView("redirect:/departments");
    }
}
