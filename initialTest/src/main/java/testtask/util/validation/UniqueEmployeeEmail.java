package testtask.util.validation;

import net.sf.oval.configuration.annotation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(checkWith = UniqueEmployeeEmailChecker.class)
@Documented
public @interface UniqueEmployeeEmail {
    String message() default "There is another employee with the same email!";
}
