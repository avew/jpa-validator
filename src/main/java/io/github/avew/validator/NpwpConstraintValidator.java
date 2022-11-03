package io.github.avew.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NpwpConstraintValidator implements ConstraintValidator<Npwp, String> {

    public static final String MESSAGE = "NPWP cannot be less or greater than 16";

    public NpwpConstraintValidator() {
    }

    @Override
    public void initialize(Npwp constraintAnnotation) {
    }

    @Override
    public boolean isValid(String npwp, ConstraintValidatorContext context) {
        if (npwp == null)
            return constraintValidatorMessage("value cannot be null", context);
        switch (npwp.length()) {
            case 15:
            case 16:
                return true;
            default:
                return constraintValidatorMessage(MESSAGE, context);
        }

    }

    private boolean constraintValidatorMessage(String message, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return false;
    }
}
