package testtask.util.validation;


import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import testtask.exception.DAOException;
import testtask.model.Employee;
import testtask.service.EmployeeService;
import testtask.service.impl.EmployeeServiceImpl;

import java.sql.SQLException;

public class UniqueEmployeeEmailChecker extends AbstractAnnotationCheck<UniqueEmployeeEmail> {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) throws OValException {
        try {
            Employee validatedEmployee = (Employee) validatedObject;
            Employee employeeFromDataBase = employeeService.getByEmail(value.toString());
            String employeeEmail = employeeFromDataBase.getEmail();
            if (!value.toString().equals(employeeEmail)) {
                return true;
            } else if (employeeFromDataBase.getId() == validatedEmployee.getId()) {
                return true;
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return false;

    }
}
