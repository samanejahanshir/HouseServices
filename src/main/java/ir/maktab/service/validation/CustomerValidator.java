package ir.maktab.service.validation;

import ir.maktab.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CustomerValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CustomerDto customerDto = (CustomerDto) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        if (!customerDto.getFirstName().matches("^[a-zA-Z]+"))
            errors.rejectValue("firstName", "Alphabetic");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
        if (!customerDto.getLastName().matches("^[a-zA-Z]+"))
            errors.rejectValue("lastName", "Alphabetic");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (!customerDto.getEmail().matches("^[\\w!#$%&'*+/=?`{|}~^-]+" +
                "(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
            errors.rejectValue("email", "Invalid.customerDto.email");
    }
}
