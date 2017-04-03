package testtask.util.validation;

import net.sf.oval.configuration.annotation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(checkWith = UniqueDepartmentNameChecker.class)
@Documented
public @interface  UniqueDepartmentName{
    String message() default "There is another department with the same name!";
}