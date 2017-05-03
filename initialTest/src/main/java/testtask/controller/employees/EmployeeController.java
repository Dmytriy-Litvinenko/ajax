package testtask.controller.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import testtask.exception.DAOException;
import testtask.exception.ValidationException;
import testtask.model.Department;
import testtask.model.Employee;
import testtask.service.impl.DepartmentServiceImpl;
import testtask.service.impl.EmployeeServiceImpl;
import testtask.util.db.StringFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        return employeeService.getAll(departmentId);
    }

    @ResponseBody
    @RequestMapping(value = "/employeeUpdate", method = RequestMethod.POST)
    public Employee updateEmployee(@RequestParam(required = false) Integer employeeId,
                                   @RequestParam(required = true) Integer departmentId) throws DAOException {
        Employee employee;
        if (employeeId == null) {
            employee = new Employee();
            employee.setDepartment(departmentService.getById(departmentId));
        } else employee = employeeService.getById(employeeId);
        return employee;
    }

    @ResponseBody
    @RequestMapping(value = "/employeeSave", method = RequestMethod.POST)
    public List<Employee> saveEmployee(@RequestBody Employee employee, @RequestParam(required = true) Integer departmentId
    ) throws DAOException {
        Integer employeeId = employee.getId();
        Department department = departmentService.getById(departmentId);
        employee.setDepartment(department);
        try {
            if (employeeId == null) employeeService.addEmpl(employee);
            else employeeService.updateEmpl(employee);
        } catch (ValidationException exception) {
            ModelAndView modelAndView = new ModelAndView("/employees/update");
            Map<String, String> map = exception.getMapError();
            modelAndView.addObject("errors", map);
            //modelAndView.addObject("departmentId", departmentId);
            modelAndView.addObject("employee", employee);
        }
        return employeeService.getAll(departmentId);//employee;//new ModelAndView("redirect:/employees?departmentId=" + departmentId);
    }

    @PostMapping("/uniqueEmail")
    public void validate(HttpServletRequest request, HttpServletResponse response) throws DAOException, IOException {
        String email = request.getParameter("email");
        String value = request.getParameter("id");
        Integer id = null;
        if (!value.equals("")) id = StringFormatter.stringToInteger(value);
        Employee employee = employeeService.getByEmail(email);
        Boolean result = true;
        if (email.equals(employee.getEmail()) && (id == null || id != employee.getId().intValue())) result = false;
        response.getWriter().write(result.toString());
    }
}


