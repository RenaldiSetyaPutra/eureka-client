package com.example.eurekaclient.Validator.Constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.eurekaclient.Pojo.Constraint.CurrencyLengthConstraint;

public class CurrencyLengthValidator implements ConstraintValidator<CurrencyLengthConstraint, String> {

    int length;
    @Override
    public void initialize(CurrencyLengthConstraint lengthOptionalConstaint) {
        this.length = lengthOptionalConstaint.length();
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null)
            return true;

        if(value.isEmpty())
            return true;

        if (value.length() == this.length) 
            return true;

        return false;

    }
}
