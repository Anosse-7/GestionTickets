package com.example.gestionticket.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {

    private String field;

    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = getFieldFieldValue(context, field);
        return value != null && value.equals(fieldValue);
    }

    private Object getFieldFieldValue(ConstraintValidatorContext context, String fieldName) {
        try {
            Object rootBean = context.unwrap(Object.class); // Unwrap to the root object
            if (rootBean != null) {
                return rootBean.getClass().getDeclaredField(fieldName).get(rootBean);
            }
            return null;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }
}
