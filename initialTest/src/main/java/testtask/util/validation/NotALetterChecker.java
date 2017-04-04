package testtask.util.validation;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;


public class NotALetterChecker extends AbstractAnnotationCheck<NotALetter> {

    @Override
    public boolean isSatisfied(Object validatedObject, Object valueToValidate, OValContext context, Validator validator) throws OValException {

        if(valueToValidate!=null) return true;
        else return false;
    }
}
