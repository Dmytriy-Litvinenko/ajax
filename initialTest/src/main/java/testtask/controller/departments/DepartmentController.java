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
import testtask.util.db.StringFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public List<Department> saveDepartment(@RequestBody Department department) throws DAOException {

        //JsonObject jsonObject = new JsonObject();JsonObject
        // ResponseEntity responseEntity = null;// = new ResponseEntity();

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
            //MultiValueMap<String, String> errors = new MultiValueMap<String, String>(map) ;          }
            //modelAndView.addObject("errors", map);
            //modelAndView.addObject("department", department);
            //return modelAndView;
            //responseEntity = new ResponseEntity(department, map, HttpStatus.NOT_ACCEPTABLE);
            //jsonObject.setErrors(map);
            //return jsonObject;
        }
        //return new ModelAndView("redirect:/departments");
        //jsonObject.setDepartments(departmentService.getAll());
        //return jsonObject;//department;
        return departmentService.getAll();
    }


    @PostMapping("/uniqueName")
    public void validate(HttpServletRequest request, HttpServletResponse response) throws DAOException, IOException {
        String name = request.getParameter("name");
        String value = request.getParameter("id");
        Integer id = null;
        if (!value.equals("")) id = StringFormatter.stringToInteger(value);
        Department department = departmentService.getByName(name);
        Boolean result = !name.equals(department.getName()) || id != null && id == department.getId().intValue();
        response.getWriter().write(result.toString());
    }
}
