package com.yotaku.exchanger.exchangerapi.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidCurrencyFormatValidator implements
        ConstraintValidator<ValidCurrencyFormat, String> {

    @Override
    public boolean isValid(final String currency, final ConstraintValidatorContext cxt) {
        return currency != null && currency.length() == 3 && currency.chars().allMatch(Character::isLetter);
    }

}