package testtask.util.validation;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import org.apache.commons.lang3.StringUtils;
import testtask.model.Employee;

import java.util.regex.Pattern;

public class NotALetterChecker extends AbstractAnnotationCheck<NotALetter> {

    //private static final String NUMBER_PATTERN = "[0-9]+";

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) throws OValException {
        //if(Pattern.compile(NUMBER_PATTERN).matcher(valueToValidate.toString()).matches()) return true;StringUtils.isNumeric(valueToValidate.toString())
        Employee employee= (Employee) validatedObject;
        if(valueToValidate!=null) return true;
        else return false;
    }
}
