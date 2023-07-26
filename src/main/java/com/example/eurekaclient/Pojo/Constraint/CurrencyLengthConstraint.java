package com.example.eurekaclient.Pojo.Constraint;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.eurekaclient.Validator.Constraint.CurrencyLengthValidator;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CurrencyLengthValidator.class)
public @interface CurrencyLengthConstraint {
    String message() default "Validation Error";
    Class<?>[] groups() default {};
    int length() default Integer.MAX_VALUE;
    Class<? extends Payload>[] payload() default {};
}
