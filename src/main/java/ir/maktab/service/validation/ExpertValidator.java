package ir.maktab.service.validation;

import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.ExpertDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ExpertValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ExpertDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ExpertDto expertDto = (ExpertDto) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        if (!expertDto.getFirstName().matches("^[a-zA-Z]+"))
            errors.rejectValue("firstName", "Alphabetic");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
        if (!expertDto.getLastName().matches("^[a-zA-Z]+"))
            errors.rejectValue("lastName", "Alphabetic");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (!expertDto.getEmail().matches("^[\\w!#$%&'*+/=?`{|}~^-]+" +
                "(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
            errors.rejectValue("email", "Invalid.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "image", "NotEmpty");
        if (!(expertDto.getImage().length < 300000)) {
            errors.rejectValue("image", "Invalid.expertDto.image.length");

        }
    }
}
