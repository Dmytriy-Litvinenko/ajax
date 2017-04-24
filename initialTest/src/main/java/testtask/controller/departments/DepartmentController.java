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

    @GetMapping(value = "/departments/{id}", headers = "Accept=application/json")//, method = RequestMethod.GET
    public @ResponseBody
    Department showDepartmentById(@PathVariable Integer id) throws DAOException {
        return departmentService.getById(id);
    }

    @ResponseBody
    @GetMapping(value = "/departments")//, method = RequestMethod.GET
    public List<Department> showDepartments() throws DAOException {
        return departmentService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.GET)//@PathVariable Integer id)
    public List<Department> deleteDepartment(@RequestParam(required = false) Integer id) throws DAOException {
        departmentService.delDep(id);
        return departmentService.getAll();
    }

    @ResponseBody
    @PostMapping(value = "/departments/{id}")//@RequestParam(required = false) Integer departmentId
    public Department updateDepartment(@PathVariable Integer id, Model model) throws DAOException {
        Department department;
        if (id == null) department = new Department();
        else department = departmentService.getById(id);
        return department;
    }

    @PutMapping(value = "/departments")//, method = RequestMethod.POST
    public ModelAndView saveDepartment(@RequestBody Department department) throws DAOException {
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
