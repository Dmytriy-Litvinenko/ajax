package testtask.util.validation;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import testtask.model.Employee;

import java.util.regex.Pattern;


public class FullNameChecker extends AbstractAnnotationCheck<FullName> {

    private static final String FULL_NAME_PATTERN = "^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$";
    //"[_A-Z][a-z]+( )[_A-Z][a-z]+";
             //+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) throws OValException {

        if(Pattern.compile(FULL_NAME_PATTERN).matcher(valueToValidate.toString()).matches()) return true;
        else return false;
    }
}
