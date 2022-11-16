package io.github.avew.validator;

import lombok.SneakyThrows;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordConstraintValidation, String> {

    @SneakyThrows
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {


        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 64),
                new LowercaseCharacterRule(1),
                new UppercaseCharacterRule(1),
                new DigitCharacterRule(1),
                new SpecialCharacterRule(1),
                new AlphabeticalCharacterRule(3),
                new NumericalSequenceRule(3, false),
                new QwertySequenceRule(3, false),
                new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
////		context.buildConstraintViolationWithTemplate(
////						Joiner.on(",").join(validator.getMessages(result)))
////				.addConstraintViolation();
        validator.getMessages(result)
                .forEach(message -> context.buildConstraintViolationWithTemplate(message).addConstraintViolation());
        return false;

    }
}
