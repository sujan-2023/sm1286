package com.sm1286.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PositiveDoubleValidator implements ConstraintValidator<PositiveDouble, Double> {

    @Override
    public void initialize(PositiveDouble constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null && value > 0;
    }
}