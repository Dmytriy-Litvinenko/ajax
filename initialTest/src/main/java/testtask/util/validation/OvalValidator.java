package testtask.util.validation;


import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.context.OValContext;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OvalValidator {

    private Validator validator = new Validator();

    public void validate(Object object) throws ValidationException  {

        List<ConstraintViolation> violations = validator.validate(object);

        Map<String,String> map = new HashMap<>();

        if(violations.size()>0){
            for(ConstraintViolation constraintViolation : violations){
                OValContext context = constraintViolation.getContext();
                if (context instanceof FieldContext) {
                    map.put(((FieldContext) context).getField().getName()//"This field"
                             , constraintViolation.getMessage());
                }
            }
            throw new ValidationException(map);
        }
    }
}
