package testtask.util.validation;

import net.sf.oval.configuration.annotation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(checkWith = NotRussianLettersChecker.class)
@Documented
public @interface NotRussianLetters {
    String message() default "Use only english letters!";
}