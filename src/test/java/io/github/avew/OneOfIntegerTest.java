package io.github.avew;

import io.github.avew.domain.Person;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.After;
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
public class OneOfIntegerTest {

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
    public void testOneOfIntegerSuccess() {
        Person person = Person.builder().npwp("012345678901235").facilities(0).gender("L").build();
        Assert.assertEquals(15, person.getNpwp().length());
        Set<ConstraintViolation<Person>> validate = validator.validate(person);
        Assert.assertTrue(validate.isEmpty());
    }

    @Test
    public void testOneOIntegerfFailed() {
        Person person = Person.builder().npwp("012345678901235").facilities(10).build();
        Assert.assertEquals(15, person.getNpwp().length());
        Set<ConstraintViolation<Person>> validate = validator.validate(person);
        Assert.assertFalse(validate.isEmpty());
    }


}
