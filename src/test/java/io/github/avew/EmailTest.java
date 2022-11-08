package io.github.avew;

import io.github.avew.domain.PersonEmail;
import io.github.avew.domain.PersonPassword;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class EmailTest {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testEmailFailed() {
        PersonEmail person = PersonEmail.builder().email("email@1").build();
        Set<ConstraintViolation<PersonEmail>> validate = validator.validate(person);
        validate.forEach(p -> System.out.println(p.getMessage()));
        Assert.assertFalse(validate.isEmpty());
    }

    @Test
    public void testEmailSuccess() {
        PersonEmail person = PersonEmail.builder().email("me@mail.com").build();
        Set<ConstraintViolation<PersonEmail>> validate = validator.validate(person);
        Assert.assertTrue(validate.isEmpty());
    }


}
