package testtask.util.validation;


import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;
import net.sf.oval.integration.spring.SpringCheckInitializationListener;
import org.springframework.stereotype.Component;
import testtask.exception.ValidationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OvalValidator {

    private Validator validator;// = new Validator();

    public OvalValidator() {
        AnnotationsConfigurer myConfigurer = new AnnotationsConfigurer();
        myConfigurer.addCheckInitializationListener(SpringCheckInitializationListener.INSTANCE);
        validator = new Validator(myConfigurer);
    }

    public void validate(Object object) throws ValidationException {

        List<ConstraintViolation> violations = validator.validate(object);
        Map<String, String> map = new HashMap<>();
        if (violations.size() > 0) {
            for (ConstraintViolation constraintViolation : violations) {
                OValContext context = constraintViolation.getContext();
                if (context instanceof FieldContext) {
                    map.put(((FieldContext) context).getField().getName(), constraintViolation.getMessage());
                }
            }
            throw new ValidationException(map);
        }
    }
}
