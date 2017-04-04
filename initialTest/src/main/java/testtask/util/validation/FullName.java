package testtask.util.validation;

import net.sf.oval.configuration.annotation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(checkWith = FullNameChecker.class)
@Documented
public @interface FullName {
    String message() default "Please, enter name and surname";
}
