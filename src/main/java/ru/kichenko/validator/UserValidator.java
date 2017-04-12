package ru.kichenko.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ru.kichenko.model.UpdateUserDataDto;
import ru.kichenko.model.User;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UpdateUserDataDto user = (UpdateUserDataDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.userForm", "Поле должно быть заполнено");
        if (user.getFirstName().length() < 3 || user.getFirstName().length() > 32) {
            errors.rejectValue("firstName", "Size.userForm.firstName", "Имя от 3 до 32 символов");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "secondName", "NotEmpty.userForm", "Поле должно быть заполнено");
        if (user.getSecondName().length() < 3 || user.getSecondName().length() > 32) {
            errors.rejectValue("secondName", "Size.userForm.secondName", "Фамилия от 3 до 32 символов");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm", "Поле должно быть заполнено");
    }
}
