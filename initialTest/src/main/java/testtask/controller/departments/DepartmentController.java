package testtask.controller.departments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
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
    public JsonObject saveDepartment(@RequestBody Department department) throws DAOException {

        JsonObject jsonObject = new JsonObject();
        ResponseEntity responseEntity=null;// = new ResponseEntity();

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
            jsonObject.setErrors(map);
            return jsonObject;
        }
        //return new ModelAndView("redirect:/departments");
        jsonObject.setDepartments(departmentService.getAll());
        return jsonObject;//department;
    }
/*
    public ResponseEntity(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers);
        Assert.notNull(status, "HttpStatus must not be null");
        this.statusCode = status;
    }

    private ResponseEntity(T body, MultiValueMap<String, String> headers, Object statusCode) {
        super(body, headers);
        this.statusCode = statusCode;
    }*/
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

    @ResponseBody
    @PostMapping("/uniqueName")
    public Boolean validate(@RequestParam Integer id, @RequestParam String name) throws DAOException {
        //Integer id = null ;!value.equals("")
        //if (id!=null) {id = Integer.parseInt(value);}
        Department department = departmentService.getByName(name);
        /*if (name.equals(department.getName())) {
            if (id == null) {
                return false;
            } else if (id != department.getId().intValue()) {
                return false;
            } else return true;
        } else return true;*/
        return !name.equals(department.getName()) || id != null && id == department.getId().intValue();
    }


}
