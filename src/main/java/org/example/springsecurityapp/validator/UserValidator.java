package org.example.springsecurityapp.validator;

import org.example.springsecurityapp.model.User;
import org.example.springsecurityapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * Валидатор для {@link org.example.springsecurityapp.model.User}
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService service;

    /**
     * Подтверждает, что объект является экземпляром User.
     *
     * @param clazz объект проверки.
     * @return boolean.
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Objects.requireNonNull(target, "target can`t be null");
        Objects.requireNonNull(errors, "errors can`t be null");
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getUserName().length() < 8 || user.getUserName().length() > 32)
            errors.reject("username", "Size.userForm.username");

        if (service.findByUserName(user.getUserName()) != null)
            errors.rejectValue("username", "Duplicate.userForm.username");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32)
            errors.reject("password", "Size.userForm.password");

        if (!user.getConfirmPassword().equals(user.getPassword()))
            errors.reject("confirmPassword", "Different.userForm.password");
    }
}