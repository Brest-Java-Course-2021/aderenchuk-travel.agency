package com.aderenchuk.brest.service.web_app.validators;

import com.aderenchuk.brest.model.Client;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.aderenchuk.brest.service.constants.ClientConstants.FIRST_NAME_SIZE;
import static com.aderenchuk.brest.service.constants.ClientConstants.LAST_NAME_SIZE;


@Component
public class ClientValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "firstname", "firstname.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastname", "lastname.empty");
        Client  client = (Client) target;

        if (StringUtils.hasLength(client.getFirstName())
                && client.getFirstName().length() > FIRST_NAME_SIZE ) {
            errors.rejectValue("firstname", "firstname.maxSize");
        }
        if (StringUtils.hasLength(client.getLastName())
                && client.getLastName().length() > LAST_NAME_SIZE ) {
            errors.rejectValue("lastname", "lastname.maxSize");
        }
    }
}
