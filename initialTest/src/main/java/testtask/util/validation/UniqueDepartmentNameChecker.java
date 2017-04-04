package testtask.util.validation;


import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import testtask.dao.DepartmentDao;
import testtask.dao.impl.DepartmentDaoImpl;
import testtask.model.Department;
import testtask.service.DepartmentService;
import testtask.service.impl.DepartmentServiceImpl;

import java.sql.SQLException;

public class UniqueDepartmentNameChecker extends AbstractAnnotationCheck<UniqueDepartmentName> {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) throws OValException {
        try {
            Department validatedDepartment = (Department) validatedObject;
            Department departmentFromDataBase = departmentService.getByName(value.toString());
            String departmentName = departmentFromDataBase.getName();
            if(!value.toString().equals(departmentName)){
                return true;
            } else if(departmentFromDataBase.getId()==validatedDepartment.getId()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }
}