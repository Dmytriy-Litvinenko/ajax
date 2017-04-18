package testtask.controller.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import testtask.exception.DAOException;
import testtask.exception.ValidationException;
import testtask.model.Employee;
import testtask.service.impl.DepartmentServiceImpl;
import testtask.service.impl.EmployeeServiceImpl;
import testtask.util.db.StringFormatter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private DepartmentServiceImpl departmentService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public ModelAndView showEmployees(@RequestParam(required = true) Integer departmentId) throws DAOException {
        List<Employee> employees = employeeService.getAll(departmentId);
        ModelAndView modelAndView = new ModelAndView("/employees/all");
        modelAndView.addObject("employees", employees);
        modelAndView.addObject("departmentId", departmentId);
        return modelAndView;
    }

    @RequestMapping(value = "/empDelete", method = RequestMethod.GET)//
    public String deleteEmployee(@RequestParam(required = true) Integer employeeId) throws DAOException {
        Integer departmentId = employeeService.getById(employeeId).getDepartment().getId();
        employeeService.delEmpl(employeeId);
        return "redirect:/employees?departmentId=" + departmentId;
    }

    @RequestMapping(value = "/employeeUpdate", method = RequestMethod.POST)//
    public ModelAndView updateEmployee(@RequestParam(required = false) Integer employeeId,
                                         @RequestParam(required = true) Integer departmentId) throws DAOException {
        Employee employee;
        if (employeeId == null) employee = new Employee();
        else employee = employeeService.getById(employeeId);
        ModelAndView modelAndView = new ModelAndView("employees/update");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("departmentId", departmentId);
        return modelAndView;
    }

    @RequestMapping(value = "/employeeSave", method = RequestMethod.POST)//
    public ModelAndView saveEmployee(@RequestParam(required = true) Integer departmentId,
                                       @RequestParam(required = false) Integer employeeId,
                                       @RequestParam(required = true) String employeeName,
                                       @RequestParam(required = true) String employeeEmail,
                                       @RequestParam(required = true) String employeeSalary,
                                       @RequestParam(required = true) String employeeBirthDate) throws DAOException {
        ModelAndView modelAndView = new ModelAndView("/employees/all");
        Employee employee = new Employee();//
        employee.setName(employeeName);
        employee.setEmail(employeeEmail);
        employee.setSalary(StringFormatter.stringToDouble(employeeSalary));//
        employee.setBirthDate(StringFormatter.stringToDate(employeeBirthDate));//
        employee.setDepartment(departmentService.getById(departmentId));
        try {
            if (employeeId == null) {
                employeeService.addEmpl(employee);
            } else {
                employee.setId(employeeId);//StringFormatter.stringToInteger(employeeId)
                employeeService.updateEmpl(employee);
            }
        } catch (ValidationException exception) {
            modelAndView = new ModelAndView("/employees/update");
            Map<String, String> map = exception.getMapError();
            modelAndView.addObject("errors", map);
            modelAndView.addObject("departmentId", departmentId);
            modelAndView.addObject("employee", employee);
        }
        modelAndView.addObject("departmentId", departmentId);
        return modelAndView;
    }
}


