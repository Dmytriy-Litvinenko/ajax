package testtask.util.validation;

import net.sf.oval.constraint.CheckWithCheck;
import testtask.model.Employee;
import testtask.service.EmployeeService;
import testtask.service.impl.EmployeeServiceImpl;

import java.sql.SQLException;

public class OvalUniqueEmail implements CheckWithCheck.SimpleCheck {

    private EmployeeService employeeService = new EmployeeServiceImpl();

    public boolean isSatisfied(Object validatedObject, Object validatedValue) {
        try {
            Employee validatedEmployee = (Employee) validatedObject;
            Employee employeeFromDataBase = employeeService.getByEmail(validatedValue.toString());
            String email = employeeFromDataBase.getEmail();
            if (!validatedValue.equals(email)) return true;
            else if (employeeFromDataBase.getId() == validatedEmployee.getId()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}