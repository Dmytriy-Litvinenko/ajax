package testtask.controller.departments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        public ModelAndView showInd() throws DAOException {
            List<Department> departments = departmentService.getAll();
            ModelAndView modelAndView = new ModelAndView("/WEB-INF/pages/departments/all.jsp");
            modelAndView.addObject("departments", departments);
            return modelAndView;
        }
    }
