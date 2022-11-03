package io.github.avew;

import io.github.avew.domain.Person;
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
public class OneOfStringTest {

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
    public void testOneOfStringSuccess() {
        Person person = Person.builder().npwp("012345678901235").facilities(0).gender("L").build();
        Assert.assertEquals("L", person.getGender());
        Set<ConstraintViolation<Person>> validate = validator.validate(person);
        Assert.assertTrue(validate.isEmpty());
    }

    @Test
    public void testOneOfStringFailed() {
        Person person = Person.builder().npwp("012345678901235").facilities(10).gender("p").build();
        Assert.assertEquals("p", person.getGender());
        Set<ConstraintViolation<Person>> validate = validator.validate(person);
        Assert.assertFalse(validate.isEmpty());
    }


}
