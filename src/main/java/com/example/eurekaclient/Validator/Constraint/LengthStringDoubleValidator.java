package com.example.eurekaclient.Validator.Constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.eurekaclient.Pojo.Constraint.LengthStringDoubleConstraint;

public class LengthStringDoubleValidator implements ConstraintValidator<LengthStringDoubleConstraint, String> {
      int length;
    int desimal;
    // String al;
    @Override
    public void initialize(LengthStringDoubleConstraint lengthOptionalConstaint) {
        this.length = lengthOptionalConstaint.digit();
        this.desimal = lengthOptionalConstaint.desimal();
        // this.al = lengthOptionalConstaint.payload();
    }
  
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null)
            return true;
            
        if(!value.isEmpty()){
            String[] str = value.split("\\.", 2);
            if(str.length < 2){
                return false;
            }
            return (str[0].length() <= this.length) && (str[1].length() == this.desimal);
        }
        return true;
    }  
}
