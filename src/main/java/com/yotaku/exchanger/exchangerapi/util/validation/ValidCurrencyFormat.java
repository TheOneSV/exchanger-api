package com.yotaku.exchanger.exchangerapi.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidCurrencyFormatValidator.class)
@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCurrencyFormat {
    String message() default "Invalid currency format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}