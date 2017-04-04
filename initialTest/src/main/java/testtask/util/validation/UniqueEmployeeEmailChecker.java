package testtask.util.validation;


import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import testtask.dao.EmployeeDao;
import testtask.dao.impl.EmployeeDaoImpl;
import testtask.model.Employee;

import java.sql.SQLException;

public class UniqueEmployeeEmailChecker extends AbstractAnnotationCheck<UniqueEmployeeEmail> {

    private EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) throws OValException {
        try {
            Employee validatedEmployee = (Employee) validatedObject;
            Employee employeeFromDataBase = employeeDao.getByEmail(value.toString());
            String employeeEmail = employeeFromDataBase.getEmail();
            if(!value.toString().equals(employeeEmail)){
                return true;
            } else if(employeeFromDataBase.getId()==validatedEmployee.getId()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }
}
