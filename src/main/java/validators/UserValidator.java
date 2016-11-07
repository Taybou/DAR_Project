package validators;

import bean.User;
import errors.Error;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Map;
import java.util.Set;

/**
 * BooXchange Project
 * Created by Al on 03-Nov-16.
 */
public class UserValidator {

    public static void validateUser(User user, Map<String, Object> errors) {
        //Validating the User:
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure().buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            // There are Errors
            for (ConstraintViolation<User> violation: violations) {
                // Copying errors into HashMap
                errors.put(violation.getPropertyPath().toString(), new Error(violation.getMessage()));
            }
        }
    }
}
