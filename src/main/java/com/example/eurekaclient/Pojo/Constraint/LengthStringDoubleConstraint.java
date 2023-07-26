package com.example.eurekaclient.Pojo.Constraint;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.eurekaclient.Validator.Constraint.LengthStringDoubleValidator;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = LengthStringDoubleValidator.class)
public @interface LengthStringDoubleConstraint {
    String message();
    Class<?>[] groups() default {};
    int digit() default Integer.MAX_VALUE;
    int desimal() default 2;
    Class<? extends Payload>[] payload() default {};
}
