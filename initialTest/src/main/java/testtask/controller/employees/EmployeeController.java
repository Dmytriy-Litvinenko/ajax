package testtask.controller.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import testtask.exception.DAOException;
import testtask.exception.ValidationException;
import testtask.model.Employee;
import testtask.service.impl.DepartmentServiceImpl;
import testtask.service.impl.EmployeeServiceImpl;

import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private DepartmentServiceImpl departmentService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    @ResponseBody
    @GetMapping(value = "/employees")
    public List<Employee> showEmployees(@RequestParam(required = true) Integer departmentId) throws DAOException {
        return employeeService.getAll(departmentId);
    }

    @ResponseBody
    @RequestMapping(value = "/empDelete", method = RequestMethod.POST)
    public List<Employee> deleteEmployee(@RequestParam(required = true) Integer employeeId) throws DAOException {
        Integer departmentId = employeeService.getById(employeeId).getDepartment().getId();
        employeeService.delEmpl(employeeId);
        return employeeService.getAll(departmentId);//"redirect:/employees?departmentId=" + departmentId;
    }

    @ResponseBody
    @RequestMapping(value = "/employeeUpdate", method = RequestMethod.POST)
    public Employee updateEmployee(@RequestParam(required = false) Integer employeeId
            , @RequestParam(required = true) Integer departmentId
    ) throws DAOException {
        Employee employee;
        if (employeeId == null) {
            employee = new Employee();
            employee.setDepartment(departmentService.getById(departmentId));
        }
        else employee = employeeService.getById(employeeId);
        return employee;
    }

    @ResponseBody
    @RequestMapping(value = "/employeeSave", method = RequestMethod.POST)
    public Employee saveEmployee(//@ModelAttribute("employee")
                                 //@RequestBody
                                         Employee employee
            , @RequestParam(required = true) Integer departmentId
    ) throws DAOException {
        Integer employeeId = employee.getId();
        employee.setDepartment(departmentService.getById(departmentId));
        try {
            if (employeeId == null) {
                employeeService.addEmpl(employee);
            } else {
                employee.setId(employeeId);
                //employeeService.getById(employeeId);
                employeeService.updateEmpl(employee);
            }
        } catch (ValidationException exception) {
            ModelAndView modelAndView = new ModelAndView("/employees/update");
            Map<String, String> map = exception.getMapError();
            modelAndView.addObject("errors", map);
            //modelAndView.addObject("departmentId", departmentId);
            modelAndView.addObject("employee", employee);
            //return modelAndView;
        }
        return employee;//new ModelAndView("redirect:/employees?departmentId=" + departmentId);
    }
}


