package testtask.util.validation;


import net.sf.oval.constraint.CheckWithCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import testtask.exception.DAOException;
import testtask.model.Department;
import testtask.service.DepartmentService;
import testtask.service.impl.DepartmentServiceImpl;

import java.sql.SQLException;

@Component
public class OvalUniqueDepartmentName implements CheckWithCheck.SimpleCheck {

    @Autowired
    private DepartmentServiceImpl departmentService; //= new DepartmentServiceImpl();

    public boolean isSatisfied(Object validatedObject, Object validatedValue) {
        try {
            Department validatedDepartment = (Department) validatedObject;
            Department departmentFromDataBase = departmentService.getByName(validatedValue.toString());
            String departmentName = departmentFromDataBase.getName();
            if (!validatedValue.equals(departmentName)) return true;
            else if (departmentFromDataBase.getId() == validatedDepartment.getId()) return true;
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return false;

    }

}
