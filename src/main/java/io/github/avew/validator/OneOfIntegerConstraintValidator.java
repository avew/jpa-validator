package io.github.avew.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class OneOfIntegerConstraintValidator implements ConstraintValidator<OneOfInteger, Integer> {

    private Set<Integer> allowedValues;

    @Override
    public void initialize(OneOfInteger constraintAnnotation) {
        allowedValues = Arrays.stream(constraintAnnotation.value()).boxed().collect(toSet());
    }

    @Override
    public boolean isValid(Integer i, ConstraintValidatorContext context) {
        if (allowedValues.contains(i)) {
            return true;
        } else {
            return constraintValidatorMessage(context);
        }
    }

    private boolean constraintValidatorMessage(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate() + ", value accepted is " + allowedValues.stream().map(String::valueOf).collect(Collectors.joining(","))).addConstraintViolation();
        return false;
    }
}
