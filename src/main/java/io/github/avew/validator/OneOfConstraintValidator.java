package io.github.avew.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class OneOfConstraintValidator implements ConstraintValidator<OneOf, Integer> {

    private Set<Integer> allowedValues;

    @Override
    public void initialize(OneOf constraintAnnotation) {
        allowedValues = Arrays.stream(constraintAnnotation.value()).boxed().collect(toSet());
    }

    @Override
    public boolean isValid(Integer i, ConstraintValidatorContext context) {
        return i == null || allowedValues.contains(i);
    }

    private boolean constraintValidatorMessage(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("value must match one of the values in the list").addConstraintViolation();
        return false;
    }
}
