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
import testtask.util.JsonObject;

import java.util.List;
import java.util.Map;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    /*@GetMapping(value = "/departments/{id}", headers = "Accept=application/json")//, method = RequestMethod.GET
    public @ResponseBody
    Department showDepartmentById(@PathVariable Integer id) throws DAOException {
        return departmentService.getById(id);
    }*/

    @ResponseBody
    @GetMapping(value = "/departments")//, method = RequestMethod.GET
    public List<Department> showDepartments() throws DAOException {
        return departmentService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)//)@PathVariable Integer id
    public List<Department> deleteDepartment(@RequestParam(required = false) Integer id) throws DAOException {
        departmentService.delDep(id);
        return departmentService.getAll();
    }

    @ResponseBody
    @PostMapping(value = "/update")//@PathVariable Integer id
    public Department updateDepartment(@RequestParam(required = false) Integer id, Model model) throws DAOException {
        Department department;
        if (id == null) department = new Department();
        else department = departmentService.getById(id);
        return department;
    }

    @ResponseBody
    @RequestMapping(value = "/saveDep", method = RequestMethod.POST)
    public Department saveDepartment(@RequestBody Department department) throws DAOException {

        JsonObject jsonObject = new JsonObject();

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
            //return modelAndView;
        }
        //return new ModelAndView("redirect:/departments");
        return department;
    }

    /*@RequestMapping(value = "/depSaveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse addNewOne(@RequestBody Department department) throws SQLException {
        JsonResponse result = new JsonResponse();
        try {
            depServ.saveOrUpdate(department);
            result.setStatus("SUCCESS");
            result.setDepartment(depServ.findAll());

        } catch (ValidationException e) {
            Map<String, String> error = e.getError();
            result.setError(error);
            result.setStatus("FAIL");

        }
        return result;
    }*/

}
