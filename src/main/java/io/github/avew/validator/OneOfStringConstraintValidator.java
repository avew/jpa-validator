package io.github.avew.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class OneOfStringConstraintValidator implements ConstraintValidator<OneOfString, String> {

    private Set<String> allowedValues;
    private boolean nullable;

    @Override
    public void initialize(OneOfString constraintAnnotation) {
        allowedValues = Arrays.stream(constraintAnnotation.value()).collect(toSet());
        nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(String i, ConstraintValidatorContext context) {

        if (!nullable) {
            if (i == null || i.isEmpty() || i.isBlank())
                i = null;
            if (allowedValues.contains(i)) {
                return true;
            } else {
                return constraintValidatorMessage(context);
            }
        }
        return true;
    }

    private boolean constraintValidatorMessage(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate() + ", value value accepted is " + String.join(",", allowedValues)).addConstraintViolation();
        return false;
    }
}
