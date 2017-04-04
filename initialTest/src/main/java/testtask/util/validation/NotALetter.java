package testtask.util.validation;

import net.sf.oval.configuration.annotation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(checkWith = NotALetterChecker.class)
@Documented
public @interface NotALetter {
    String message() default "This is not a number";

}
